class TLocalStorage {
    constructor(id) {
        this.id = id;
        this.ls = localStorage[this.id];
        this.list = {};
        if (this.ls) {
            this.lsParse = JSON.parse(this.ls);
        }
    }

    addValue(key, value) {
        if (key && value) {
            if (this.ls) {
                this.lsParse[key] = value;
                this.ls = JSON.stringify(this.lsParse);
            }
            this.list[key] = value;
            localStorage[this.id] = JSON.stringify(this.list);
        } else {
            return null
        }
    }

    getValue(key) {
        if (key) {
            return this.lsParse[key];
        } else {
            return undefined;
        }
    }

    deleteValue(key) {
        if (key) {
            delete this.lsParse[key];
            localStorage[this.id] = JSON.stringify(this.lsParse);
        } else {
            return null
        }
    }

    getKeys() {
        let keyArray = [];
        for (let k in this.lsParse) {
            if (k) {
                keyArray.push(k);
            }
        }
        return keyArray;
    }
}