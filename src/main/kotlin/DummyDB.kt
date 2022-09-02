object DummyDB {
    var listOfStudents: MutableList<Student> = ArrayList()

    fun deleteStudentFromDb(studentRollNum: Int): Student? {
        var student = searchStudentFromDb(studentRollNum)
        if (student != null) {
            listOfStudents.remove(student)
        }
        return student
    }
    fun searchStudentFromDb(studentRollNum: Int): Student? {
        var student = listOfStudents.find {
            it.rollNum == studentRollNum
        }
        return student
    }
    fun addStudentToDB(student: Student) {
        listOfStudents.add(student)
    }


}