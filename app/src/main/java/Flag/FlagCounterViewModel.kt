package Flag

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class FlagCounterViewModel: ViewModel() {
    private val _flagCounter = mutableIntStateOf(0)
    val flagCounter : State<Int> = _flagCounter
    private val _flagMistakeCounter = mutableIntStateOf(0)
    val flagMistakeCounter : State<Int> = _flagMistakeCounter

    fun flagCorrectCounterIncrement(){
        _flagCounter.intValue++
    }
    fun flagMistakeCounterIncrement(){
        _flagMistakeCounter.intValue++
    }
    fun flagMistakeCounterReset(){
        _flagMistakeCounter.intValue = 0
    }
    fun flagCorrectCounterReset(){
        _flagCounter.intValue = 0
    }
    fun setFlagCount(count: Int){
        _flagCounter.intValue = 0
    }
}