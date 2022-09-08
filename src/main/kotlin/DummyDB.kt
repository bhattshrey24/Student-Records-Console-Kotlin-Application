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

    fun advanceSearch(query: String): List<Student> {
        var listOfStudentsThatMatchQuery: ArrayList<Student> = ArrayList()

        // check whether just num or num and letters
        var containsJustNum = query.length > 1 && !query.contains(regex = Regex("[a-zA-Z]+"))

        if (containsJustNum) {
            var num = query.toInt()
            listOfStudents.forEach {
                if (it.yearOfAdmission == num || it.rollNum == num) {
                    listOfStudentsThatMatchQuery.add(it)
                }
            }


        } else { // can be just letters or alphanumeric
            var containsJustLetters = query.length > 1 && !query.contains(regex = Regex("[0-9]+"))

            if (containsJustLetters) { // Just Letter

                listOfStudents.forEach {
                    var isContains = it.name.contains(regex = Regex(query))
                    if (isContains) {
                        listOfStudentsThatMatchQuery.add(it)
                    }
                }

            } else { // Alpha Numeric

                var pattern = Regex("[a-zA-Z]*") // extracts string from alphanumeric string
                var result = pattern.find(query)
                var extractedWord = result?.value

                pattern = Regex("([0-9]+)")//finds the numbers from string
                result = pattern.find(query)
                var extractedNumber = result?.value?.toInt()

                // check with name
                listOfStudents.forEach {
                    var isContains = it.name.contains(regex = Regex(extractedWord!!))
                    if (isContains) {
                        if (it.yearOfAdmission == extractedNumber || it.rollNum == extractedNumber) {
                            listOfStudentsThatMatchQuery.add(it)
                        }
                    }
                }
            }
        }

        return listOfStudentsThatMatchQuery
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