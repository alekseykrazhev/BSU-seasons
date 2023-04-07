class Application {
    constructor(id, clientName, date, workType) {
        this.id = id;
        this.clientName = clientName;
        this.date = date;
        this.workType = workType;
    }
}

class WorkPlan {
    constructor(id, date, workType, name) {
        this.id = id;
        this.date = date;
        this.workType = workType;
        this.name = name;
    }
}

const Applications = [
    new Application(0, 'Star Lord', new Date('2022-03-28 12:20:05.000000'), 1),
    new Application(1, 'Glory Vase', new Date('2022-03-26 12:18:54.000000'), 0),
    new Application(2, 'Persian King', new Date('2022-03-28 12:20:05.000000'), 2),
    new Application(3, 'Stradivarius', new Date('2022-03-23 15:10:14.000000'), 0),
    new Application(4, 'Persian King', new Date('2022-03-23 15:10:14.000000'), 1),
]

const WorkPlans = [
    new WorkPlan(0, new Date('2023-03-20 11:20:05.000000'), 0, 'Alex'),
    new WorkPlan(1, new Date('2022-03-23 11:20:05.000000'), 1, 'AAAAAAAAAA'),
    new WorkPlan(2, new Date('2022-03-21 14:40:05.000000'), 2, 'BBBBBBBBBB'),
    new WorkPlan(3, new Date('2022-03-23 15:11:05.000000'), 0, 'CCCCCCCCCC'),
]