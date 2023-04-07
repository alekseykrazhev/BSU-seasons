const ApplicationsFormInputs = [
    {
        label: 'Client name:',
        name: 'clientName',
        placeholder: 'Enter name...',
        styleClasses: [],
        type: "text",
        attributes: new Map([
            ['data-validation', 'true'],
            ['data-validation-params', 'require'],
        ])
    },
    {
        label: 'Work Type:',
        name: 'workType',
        placeholder: 'Enter type of work...',
        styleClasses: [],
        type: "number",
        attributes: new Map([
            ['data-validation', 'true'],
            ['data-validation-params', 'numbers'],
        ])
    },
]

function onLoadPage() {
    const resultContainer = document.getElementById('applications-result');

    generateForm('applications-form', ApplicationsFormInputs, "Show applications");
    generateForm('all-applications-form', [], "Show all applications");


    const form = document.getElementById('applications-form');
    form.onsubmit = (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const searchQuery = {
            clientName: formData.get('clientName'),
            workType: parseInt(formData.get('workType')),
        }

        // console.log(typeof searchQuery.workType);
        const applications = getApplicationsByQuery(Applications, searchQuery);
        // console.log(applications);
        printResult(resultContainer, applications);
    };

    const allApplicationsForm = document.getElementById('all-applications-form');
    allApplicationsForm.onsubmit = (e) => {
        e.preventDefault();
        printResult(resultContainer, Applications);
    };

    initInputValidation('applications-form');
}

function getApplicationsByQuery(applications, searchQuery) {
    return applications.filter(applications => {
        return applications.clientName === searchQuery.clientName &&
               applications.workType === searchQuery.workType;
    });
}

function printResult(resultContainer, result) {
    resultContainer.innerHTML = '';
    const resultTable = createResultTable(
        result,
        'applications-table',
        'applications-table__header',
        'applications-table__row',
        'applications-table__col',
    );
    resultContainer.append(resultTable);
}

console.log("Applications page loaded.");