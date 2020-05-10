package club.caohao.game.model

import club.caohao.game.model.obj
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Painter

class bot(override var x:Int,override var y:Int,var currentstation:station) :obj{
    var color: Color = Color.RED
    override var width: Int = 10

    override var heigth: Int = 6

    fun sending(){
        when(currentstation){
            station.UP -> y-=10
            station.DOWN -> y+=10
            station.LEFT -> x-=10
            station.RIGHT -> x+=10
        }
    }
//    fun fianl:Boolean(){
//        when(currentstation){
//            station.UP -> {return y==6:true?flase}
//            station.DOWN -> {}
//            station.LEFT -> {}
//            station.RIGHT -> {}
//        }
//        return true
//    }

    override fun draw() {
        Painter.drawColor(color,x,y,width,heigth)
    }
}