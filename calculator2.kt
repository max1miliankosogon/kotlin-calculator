fun main(){
    // Abstract Command class
    abstract class Command {
        abstract fun execute()
        abstract fun undo()
    }

    // AddCommand class
    class AddCommand(private val calculator: Calculator, private val value: Int) : Command() {
        override fun execute() {
            calculator.add(value)
        }

        override fun undo() {
            calculator.subtract(value)
        }
    }

    // SubtractCommand class
    class SubtractCommand(private val calculator: Calculator, private val value: Int) : Command() {
        override fun execute() {
            calculator.subtract(value)
        }

        override fun undo() {
            calculator.add(value)
        }
    }

    // MultiplyCommand class
    class MultiplyCommand(private val calculator: Calculator, private val value: Int) : Command() {
        override fun execute() {
            calculator.multiply(value)
        }

        override fun undo() {
            calculator.divide(value)
        }
    }

    // DivideCommand class
    class DivideCommand(private val calculator: Calculator, private val value: Int) : Command() {
        override fun execute() {
            calculator.divide(value)
        }

        override fun undo() {
            calculator.multiply(value)
        }
    }

    // Calculator class
    class Calculator {
        var value: Int = 0
            private set
        private val history: MutableList<Command> = mutableListOf()
        private val redoHistory: MutableList<Command> = mutableListOf()

        fun add(value: Int) {
            this.value += value
        }

        fun subtract(value: Int) {
            this.value -= value
        }

        fun multiply(value: Int) {
            this.value *= value
        }

        fun divide(value: Int) {
            this.value /= value
        }

        fun executeCommand(command: Command) {
            command.execute()
            history.add(command)
            redoHistory.clear()
        }

        fun undo() {
            if (history.isNotEmpty()) {
                val command = history.removeAt(history.lastIndex)
                command.undo()
                redoHistory.add(command)
            }
        }

        fun redo() {
            if (redoHistory.isNotEmpty()) {
                val command = redoHistory.removeAt(redoHistory.lastIndex)
                command.execute()
                history.add(command)
            }
        }
    }

}