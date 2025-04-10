package Capital

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CapitalCounterViewModel: ViewModel() {
    private val _capitalCounter = mutableIntStateOf(0)
    val capitalCounter : State<Int> = _capitalCounter
    private val _capitalMistakeCounter = mutableIntStateOf(0)
    val capitalMistakeCounter = _capitalMistakeCounter

    fun capitalCorrectCounterIncrement(){
        _capitalCounter.intValue++
    }
    fun capitalCorrectCounterReset(){
        _capitalCounter.intValue = 0
    }
    fun capitalMistakeCounterIncrement(){
        _capitalMistakeCounter.intValue++
    }
    fun capitalMistakeCounterReset(){
        _capitalMistakeCounter.intValue = 0
    }
    fun setFlagCount(count: Int){
        _capitalCounter.intValue = 0
    }
}