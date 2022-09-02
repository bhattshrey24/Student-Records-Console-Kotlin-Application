class StudentRecords {
    private fun addStudent() {
        print("Enter name of student: ")
        var name = readln()
        print("Enter roll number: ")
        var rollNumber = readln().toInt()

        var listOfSubject = mutableListOf<Subject>()

        for (i in 1..3) { // List of subjects
            print("Enter name of subject $i : ")
            var subName = readln()
            print("Enter Marks In $subName : ")
            var marksScored = readln().toInt()
            print("Enter Total marks In $subName : ")
            var totalMarks = readln().toInt()
            listOfSubject.add(
                Subject(
                    nameOfSubject = subName,
                    marksScored = marksScored,
                    totalMarks = totalMarks
                )
            )
        }

        var newStudent = Student(
            name,
            rollNumber,
            listOfSubject
        )
        DummyDB.addStudentToDB(newStudent)
        println("$name total marks : ${newStudent.totalMarksScored()}")
        println("$name percentage : ${newStudent.percentage()} %")
    }

    private fun deleteStudent() {
        print("Enter the roll number of the student whose records should be deleted : ")
        var rollNumber = readln().toInt()
        var deletedStudent = DummyDB.deleteStudentFromDb(rollNumber)
        if (deletedStudent == null) {
            println("Student with roll number $rollNumber not found")
        } else {
            println("Successfully deleted ${deletedStudent.name} records")
        }
    }

    private fun searchAStudentByRollNumber() { // later add search by name functionality
        print("Enter the roll number of the student to be searched : ")
        var studentRollNum = readln().toInt()
        var stud = DummyDB.searchStudentByRollNumberFromDb(studentRollNum)
        if (stud == null) {
            println("Student not found!!!")
        } else {
            displayStudentDetails(stud)
        }
    }

    private fun searchStudentByName() {
        print("Enter the name of the student to be searched : ")
        var studentName = readln()
        var stud = DummyDB.searchStudentByNameFromDB(studentName)
        if (stud == null) {
            println("Student not found!!!")
        } else {
            displayStudentDetails(stud)
        }
    }

    private fun showAllStudent() {
        println("Showing All Students")
        var listOfStudents = DummyDB.listOfStudents
        for (student in listOfStudents) {
            displayStudentDetails(student)
        }
    }

    private fun displayStudentDetails(student: Student) {
        println("Student name : ${student.name}  ")
        for (subject in student.subjects) {
            println("Marks scored in ${subject.nameOfSubject} : ${subject.marksScored}/${subject.totalMarks} ")
        }
        println()
    }


    fun showMenu() {
        var userWantsToExitFlag = false
        do {
            println("1)Add New Student")
            println("2)Delete Student")
            println("3)Search A Student")
            println("4)Show all students")
            println("5)Exit")
            print("Enter your choice : ")
            var choice = readln()
            when (choice) {
                "1" -> {
                    addStudent()
                }

                "2" -> {
                    deleteStudent()
                }
                "3" -> {
                    println("1) Search student by roll number ")
                    println("2) Search student by name ")
                    var ip = readln().toInt()
                    when (ip) {
                        1 -> {
                            searchAStudentByRollNumber()
                        }

                        2 -> {
                            searchStudentByName()
                        }

                        else -> {
                            println("Invalid Input ")
                        }
                    }
                }

                "4" -> {
                    showAllStudent()
                }

                "5" -> {
                    userWantsToExitFlag = true
                }

                else -> {
                    println("You entered invalid number")
                }
            }
        } while (!userWantsToExitFlag)
    }

}