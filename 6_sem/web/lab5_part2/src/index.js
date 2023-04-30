import { fromEvent } from 'rxjs';
import { ajax } from 'rxjs/ajax';
import { map } from 'rxjs/operators';

const showDataBtn = document.getElementById('show-data-btn');
const deleteRowBtn = document.getElementById('delete-row-btn');
const dataTable = document.getElementById('data-table');
const tableBody = dataTable.querySelector('tbody');


fromEvent(showDataBtn, 'click')
  .subscribe(() => {
    ajax.getJSON('data.json')
    .subscribe(data => {
        tableBody.innerHTML = '';
        //const dataArray = JSON.parse(data);
        // data = JSON.parse(data);
        console.log(data);
        console.log(typeof data);

        var rows = "";
        for (let i = 0; i < data.length; i++) {
            const row = data[i];
            rows += `
            <tr>
              <td>${row.tenant}</td>
              <td>${row.applicationID}</td>
              <td>${row.workType}</td>
              <td>${row.isExpired}</td>
              <td>${row.dispatcherID}</td>
            </tr>
          `;
          console.log(typeof rows);
      dataTable.querySelector('tbody').innerHTML = rows;
        }
        
    });
  });


// fromEvent(showDataBtn, 'click').subscribe(() => {
//     ajax.getJSON('/data.json').subscribe((data) => {
//         tableBody.innerHTML = ''; // clear table rows
//         //const dataArray = JSON.parse(data);
//         // data = JSON.parse(data);
//         console.log(data);
//         console.log(typeof data);

//         data.forEach((row) => {
//             const newRow = tableBody.insertRow(-1); // insert new row at the end
//             newRow.insertCell(-1).innerText = row.column1; // add column 1
//             newRow.insertCell(-1).innerText = row.column2; // add column 2
//             newRow.insertCell(-1).innerText = row.column3; // add column 3
//         });
//     });
// });


fromEvent(deleteRowBtn, 'click')
  .subscribe(() => {
        const rows = dataTable.querySelectorAll('tbody tr');
        if (rows.length > 0) {
        const lastRow = rows[rows.length - 1];
        lastRow.parentNode.removeChild(lastRow);
        }
});

console.log('Entered app');