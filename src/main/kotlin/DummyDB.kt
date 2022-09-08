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

    private fun <T> MutableList<T>.find(conditionProvidedByUser: (T) -> Boolean): T? {

        for (item in this) { // 'this' means the MutableList on which we called this 'find' function
            if (conditionProvidedByUser(item)) { // This means check according to the lambda that user
                // provided. If current item of list returns true that means this item is the
                // item we were looking for. Understand the lambda provided by
                // user ie. 'conditionProvidedByUser' as a black box which has the condition
                // which user wants to use to filter the list.
                return item
            }
        }
        return null // This means if we haven't found then element then simply return null
    }

}