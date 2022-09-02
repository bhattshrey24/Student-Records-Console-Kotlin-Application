data class Student(
    var name: String,
    var rollNum: Int,
    var subjects: MutableList<Subject>
) {
    fun totalMarksScored(): Int {
        var sum = 0
        for (subject in subjects) {
            sum += subject.marksScored
        }
        return sum
    }

    fun percentage(): Float {
        var marksScored = totalMarksScored()
        var totalMarks = 0
        for (subject in subjects) {
            totalMarks += subject.totalMarks
        }
        var fraction = (marksScored * 1.0f / totalMarks)
        return fraction * 100
    }
}
