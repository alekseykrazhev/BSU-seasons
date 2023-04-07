function createResultTable(
    applications,
    tableClass = 'table',
    headerClass = 'header',
    rowClass = 'row',
    colClass = 'col',
) {
    const table = document.createElement('table');
    const header = createResultTableHeader(applications[0], headerClass, colClass)
    const rows = applications?.map(application => createResultTableRow(application, rowClass, colClass));

    table.classList.add(tableClass);
    table.append(header, ...rows);
    return table;
}

function createResultTableHeader(application, headerClass, colStyleClass) {
    const header = document.createElement('tr');
    // console.log(application);
    const colKeys = Object.keys(application);

    const cols = colKeys.map(key => {
        const col = document.createElement('th');
        col.style.textTransform = 'uppercase';
        col.textContent = key.toString();
        col.classList.add(colStyleClass);
        return col;
    })

    header.classList.add(headerClass);
    header.append(...cols);
    return header;
}

function createResultTableRow(application, rowClass, colClass) {
    const row = document.createElement('tr');
    const colValues = Object.values(application);
    console.log(colValues);

    const cols = colValues.map(value => {
        console.log(value);
        const col = document.createElement('td');
        col.classList.add(colClass);

        if(value instanceof Date) {
            col.textContent = new Date(value).toDateString();
            console.log(value);
        } else {
            col.textContent = value.toString();
        }

        return col;
    })

    row.classList.add(rowClass);
    row.append(...cols);
    return row;
}
