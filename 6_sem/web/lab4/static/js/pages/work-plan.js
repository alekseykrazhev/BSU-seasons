const WorkPlanFormInputs = [
    {
        label: 'Work Plan Type:',
        name: 'workType',
        placeholder: 'Enter type of work...',
        styleClasses: [],
        type: "text",
        attributes: new Map([
            ['data-validation', 'true'],
            ['data-validation-params', 'numbers'],
        ])
    },
]

function onLoadPage() {
    const resultContainer = document.getElementById('workplan-result');

    generateForm('workplan-form', WorkPlanFormInputs, "Show work plan");
    generateForm('all-workplan-form', [], "Show all work plans");

    const workplanForm = document.getElementById('workplan-form');
    workplanForm.onsubmit = (e) => {
        e.preventDefault();
        // console.log('Some log');
        const formData = new FormData(e.target);
        const searchQuery = {workType: parseInt(formData.get('workType')),}

        const workplans = getWorkPlanByQuery(WorkPlans, searchQuery);
        // console.log(workplans.workType);
        // console.log(searchQuery);
        printResult(resultContainer, workplans);
    };

    const allWorkplanForm = document.getElementById('all-workplan-form');
    allWorkplanForm.onsubmit = (e) => {
        e.preventDefault();
        printResult(resultContainer, WorkPlans);
    };

    initInputValidation('workplan-form');
}


function getWorkPlanByQuery(workplan, searchQuery) {
    return workplan.filter(workplan => {
        return workplan.workType === searchQuery.workType;
    });
}

function printResult(resultContainer, result) {
    resultContainer.innerHTML = '';
    const resultTable = createResultTable(
        result,
        'workplan-table',
        'workplan-table__header',
        'workplan-table__row',
        'workplan-table__col',
    );
    resultContainer.append(resultTable);
}

console.log("WorkPlan page loaded.");