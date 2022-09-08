class StudentRecords {
    private fun addStudent() {
        print("Enter name of student: ")
        var name = readln().lowercase()
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
        println("Displaying the Mark sheet ")
        displayStudentsMarkSheet(newStudent)
        println()
    }

    private fun deleteStudent() {
        print("Enter the roll number of the student whose records should be deleted : ")
        var rollNumber = readln().toInt()
        var deletedStudent = DummyDB.deleteStudentFromDb(rollNumber)
        if (deletedStudent == null) {
            println("Student with roll number $rollNumber not found")
        } else {
            var formattedName = convertNameToProperFormat(deletedStudent.name)
            println("Successfully deleted $formattedName records")
        }
    }

    private fun convertNameToProperFormat(str: String): String {
        var splitStrList = str.split(" ")
        var fullNameStr = ""
        for (str in splitStrList) {
            var cap = str.get(0).uppercase()
            var capStr = cap + str.substring(1)
            fullNameStr += "$capStr "
        }
        return fullNameStr
    }

    private fun displayStudent(student: Student) {
        // make 1st letter of first and last name capital
        println()
        var convertedName = convertNameToProperFormat(student.name)
        println("Student Name : $convertedName ")
        println("Student Roll Number : ${student.rollNum}")
        println("Student Year Of Admission : ${student.yearOfAdmission}")
        println()
    }

    private fun searchAStudentByRollNumber() { // later add search by name functionality
        print("Enter the roll number of the student to be searched : ")
        var studentRollNum = readln().toInt()
        var stud = DummyDB.searchStudentByRollNumberFromDb(studentRollNum)
        if (stud == null) {
            println("Student not found!!!")
        } else {
            displayStudent(stud)
        }
    }

    private fun searchStudentByName() {
        print("Enter the name of the student to be searched : ")
        var studentName = readln()
        var stud = DummyDB.searchStudentByNameFromDB(studentName)
        if (stud == null) {
            println("Student not found!!!")
        } else {
            displayStudent(stud)
        }
    }

    private fun showAllStudent() {
        println("Showing All Students")
        var listOfStudents = DummyDB.listOfStudents
        for (student in listOfStudents) {
            displayStudent(student)
        }
    }

    private fun getSpacesString(numOfSpaces: Int): String {
        var spaceString = ""
        for (i in 1..numOfSpaces)
            spaceString += " "
        return spaceString
    }

    private fun displayStudentsMarkSheet(student: Student) {
        var spaceStringBtwNameAndYoaHeader = getSpacesString(18)
        var spaceStringBtwYoaAndRollNumberHeader = getSpacesString(26)
        var spaceStringBtwYoaAndRoll = getSpacesString(25)

        var formattedName = convertNameToProperFormat(student.name)

        println()
        println("NAME${spaceStringBtwNameAndYoaHeader}YOA${spaceStringBtwYoaAndRollNumberHeader}ROLL NUMBER")
        if (student.name.length < 15) { // If name is bigger than length 15 then simply break it into 2 parts and show the other part in the lower row
            var spaceCal = 15 - student.name.length + 6
            var spaceStringBtwNameAndYoa = getSpacesString(spaceCal)
            print(
                "${formattedName}$spaceStringBtwNameAndYoa${student.yearOfAdmission}" +
                        "$spaceStringBtwYoaAndRoll${student.rollNum}"
            )
        } else {
            var part1OfName = formattedName.substring(0, 15)
            var part2OfName = formattedName.substring(15)
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

    private fun advanceSearch(): List<Student>? {
        print("Enter search query: ")
        var query = readln().lowercase() // because every name in DB is stored
        // as lowercase to maintain consistency
        return DummyDB.advanceSearch(query)
    }

    private fun showStudentsUsingAdvanceSearch() {
        var listOfStudents = advanceSearch()
        if (listOfStudents.isNullOrEmpty()) {
            print("No student matched with query")
            return
        }
        println()
        println("Students that matched query are :-")
        for (stud in listOfStudents) {
            displayStudent(stud)
        }
    }

    private fun showStudentMarkSheet() {
        var listOfStudents = advanceSearch()
        if (listOfStudents != null) {
            for (student in listOfStudents) {
                displayStudentsMarkSheet(student)
            }
        }
    }

    fun showMenu() {
        var userWantsToExitFlag = false
        do {
            println("1)Add new student")
            println("2)Delete student")
            println("3)Search a student")
            println("4)Show all student details")
            println("5)Show a particular students mark sheet")
            println("6)Exit")
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
                    println("3) Advance search ")

                    var ip = readln().toInt()
                    when (ip) {
                        1 -> {
                            searchAStudentByRollNumber()
                        }

                        2 -> {
                            searchStudentByName()
                        }

                        3 -> {
                            showStudentsUsingAdvanceSearch()
                        }

                        else -> {
                            println("You entered invalid number")
                        }
                    }
                }

                "4" -> {
                    showAllStudent()
                }

                "5" -> {
                    showStudentMarkSheet()
                }

                "6" -> {
                    userWantsToExitFlag = true
                }

                else -> {
                    println("You entered invalid number")
                }
            }
        } while (!userWantsToExitFlag)
    }

}