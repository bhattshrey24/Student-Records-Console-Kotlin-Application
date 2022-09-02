object DummyDB {
    var listOfStudents: MutableList<Student> = ArrayList()
    fun deleteStudentFromDb(studentRollNum: Int): Student? {
        var student = searchStudentByRollNumberFromDb(studentRollNum)
        if (student != null) {
            listOfStudents.remove(student)
        }
        return student
    }

    fun searchStudentByRollNumberFromDb(studentRollNum: Int): Student? {
        var student = listOfStudents.find {
            it.rollNum == studentRollNum
        }
        return student
    }

    fun searchStudentByNameFromDB(studentName: String): Student? {
        var student = listOfStudents.find {
            it.name == studentName
        }
        return student
    }

    fun addStudentToDB(student: Student) {
        listOfStudents.add(student)
    }


}