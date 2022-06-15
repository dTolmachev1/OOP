import io.github.dtolmachev1.dsl.DSL

DSL().student {
    name = "DenisTolmachev"
    userName = "dtolmachev1"
    repo = "https://github.com/dtolmachev1/OOP.git"
    group = "20214"
    tasks {
        assignedTask {
            title = "Task_2_1_1"
            deadline = "23.06.2022"
        }
        assignedTask {
            title = "Task_2_2_1"
            deadline = "20.07.2022"
        }
        assignedTask {
            title = "Task_2_3_1"
            deadline = "17.08.2022"
        }
    }
    lessons {
        lesson {
            date = "08.08.2022"
            attendance = false
        }
        lesson {
            date = "05.10.2022"
            attendance = false
        }
        lesson {
            date = "16.12.2022"
            attendance = true
        }
    }
    marks {
        mark {
            score = 2
            date = "21.12.2022"
        }
        mark {
            score = 3
            date = "24.12.2022"
        }
        mark {
            score = 4
            date = 27.12.2022
        }
        mark {
            score = 5
            date = "30.12.2022"
        }
    }
}
