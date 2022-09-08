class StudentRecords {
    private fun addStudent() {
        print("Enter name of student: ")
        var name = readln()
        print("Enter year of admission: ")
        var yearOfAdmission = readln().toInt()
        print("Enter roll number: ")
        var rollNumber = readln().toInt()

        var listOfSubject = mutableListOf<Subject>()

        for (i in 1..3) { // List of subjects
            print("Enter name of subject $i : ")
            var subName = readln()
            print("Enter Marks obtained In $subName : ")
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
            yearOfAdmission,
            listOfSubject
        )
        DummyDB.addStudentToDB(newStudent)
        println()
        println("$name total marks : ${newStudent.totalMarksScored()}")
        println("$name percentage : ${newStudent.percentage()} %")
        println()
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

    private fun getSpacesString(numOfSpaces: Int): String {
        var spaceString = ""
        for (i in 1..numOfSpaces)
            spaceString += " "
        return spaceString
    }

    private fun displayStudentDetails(student: Student) {
        var spaceStringBtwNameAndYoaHeader = getSpacesString(18)
        var spaceStringBtwYoaAndRollNumberHeader = getSpacesString(26)
        var spaceStringBtwYoaAndRoll = getSpacesString(25)

        println()
        println("NAME${spaceStringBtwNameAndYoaHeader}YOA${spaceStringBtwYoaAndRollNumberHeader}ROLL NUMBER")
        if (student.name.length < 15) {
            var spaceCal = 15 - student.name.length + 7
            var spaceStringBtwNameAndYoa = getSpacesString(spaceCal)
            print(
                "${student.name}$spaceStringBtwNameAndYoa${student.yearOfAdmission}" +
                        "$spaceStringBtwYoaAndRoll${student.rollNum}"
            )
        } else {
            var part1OfName = student.name.substring(0, 15)
            var part2OfName = student.name.substring(15)
            var spaceStringBetweenP1AndYoa = getSpacesString(7)
            println(
                "$part1OfName${spaceStringBetweenP1AndYoa}${student.yearOfAdmission}" +
                        "$spaceStringBtwYoaAndRoll${student.rollNum}"
            )

            print("$part2OfName")
        }
        println()
        var spaceStrBtwSubjectAndObtainedHeader = getSpacesString(15)
        var spaceStrBtwObtainedAndTotalHeader = getSpacesString(15)
        var spaceStringBtwObtainedAndTotal = getSpacesString(27)
        println()
        println("SUBJECT${spaceStrBtwSubjectAndObtainedHeader}MARKS OBTAINED${spaceStrBtwObtainedAndTotalHeader}TOTAL MARKS")
        for (subject in student.subjects) {
            var spaceCal = 22 - subject.nameOfSubject.length
            var spaceStrBtwSubjectAndObtained = getSpacesString(spaceCal)
            println("${subject.nameOfSubject}${spaceStrBtwSubjectAndObtained}${subject.marksScored}${spaceStringBtwObtainedAndTotal}${subject.totalMarks} ")
        }
        var spaceBeforeTotal = getSpacesString(22)
        var spaceBetweenTotalAndPerHeader = getSpacesString(24)
        println("${spaceBeforeTotal}TOTAL${spaceBetweenTotalAndPerHeader}PERCENTAGE")
        var spaceBetweenTotalAndPer = getSpacesString(26)
        println("${spaceBeforeTotal}${student.totalMarksScored()}${spaceBetweenTotalAndPer}${student.percentage()}%")
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