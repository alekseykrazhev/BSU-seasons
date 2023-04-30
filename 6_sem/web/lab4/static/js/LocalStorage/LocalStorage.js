'use strict';
{
    class applicationsStorage{
        constructor() {
            this.TLocalStorage = new TLocalStorage('applications');
        }

        addValue(){
            let id = document.getElementById('id').value;
            let tenant = document.getElementById('tenant').value;
            let workType = document.getElementById('workType').value;
            let due = document.getElementById('due').value;
            let obj = {
                'id': id,
                'tenant': tenant,
                'workType': workType,
                'due': due,
            };

            if (id) {
                this.TLocalStorage.addValue(id, obj);
            } else {
                alert('Введите значения');
            }

            (function resetMainFrom() {
                document.getElementById('input-form').reset();
            })();
        }

        getValue(){
            let infoField = document.getElementById('info').value;
            let infoBlock = document.getElementById('text-info');
            if (infoBlock.childNodes.length !== 0) {
                infoBlock.innerHTML = '';
            }
            let applicationInfo = this.TLocalStorage.getValue(infoField);

            if (!(infoField in this.TLocalStorage.lsParse)) {
                alert('Такой заявки не существует');
            } else {
                for (let key in applicationInfo) {
                    let yourApplicationStr = `${key}:  ${applicationInfo[key]}`;
                    let block = document.createElement("DIV");
                    let t = document.createTextNode(yourApplicationStr);
                    block.appendChild(t);
                    infoBlock.appendChild(block);
                }
            }
            (function() {
                document.getElementById('info-form').reset();
            })();
        }

        deleteValue(){
            let deleteField = document.getElementById('delete').value;
            if (!(deleteField in this.TLocalStorage.lsParse)) {
                alert('Такой заявки не существует');
            } else {
                this.TLocalStorage.deleteValue(deleteField);
                alert('Информация о заявке удалена');
            }
            (function() {
                document.getElementById('delete-form').reset();
            })()
        }

        getKeys(){
            let listOfApplications = this.TLocalStorage.getKeys();
            let allApplicationsFrom = document.getElementById('list-applications');

            if (allApplicationsFrom.childNodes.length !== 0) {
                allApplicationsFrom.innerHTML = '';
            }

            if (listOfApplications.length === 0) {
                alert('Нет заявок');
            } else {
                for (let i = 0; i < listOfApplications.length; i++) {
                    let yourApplicationStr = listOfApplications[i];
                    let allApplicationsBlock = document.createElement("DIV");
                    let t = document.createTextNode(yourApplicationStr);
                    allApplicationsBlock.appendChild(t);
                    allApplicationsFrom.appendChild(allApplicationsBlock);
                }
            }
            this.TLocalStorage.getKeys();
        }
    }

    var applicationsTable = new applicationsStorage();
}
