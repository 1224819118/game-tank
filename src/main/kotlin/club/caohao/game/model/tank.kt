package club.caohao.game.model

import club.caohao.game.service.BlockAble
import club.caohao.game.service.Moveable
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Painter
import javax.swing.text.StyledEditorKit

class tank(var color: Color,  override var x: Int, override var y: Int) :obj,Moveable{



    override var width: Int = 40
    override var heigth: Int = 40
    var currentDirection :station = station.DOWN
    private var badstation:station? = null


    fun sendBot(): bot {
        when(currentDirection){
            station.UP -> {return bot(x+2/width,y,currentDirection)}
            station.DOWN -> {return bot(x+2/width,y+heigth,currentDirection)}
            station.LEFT -> {return bot(x,y+2/heigth,currentDirection)}
            station.RIGHT -> {return bot(x+width,y+2/heigth,currentDirection)}
        }
    }

    fun move(move: station){
        if (move == badstation){
            return
        }
        currentDirection = move
        when(move){
            station.UP -> y-=5
            station.DOWN -> y+=5
            station.LEFT -> x-=5
            station.RIGHT -> x+=5
        }
    }

    override fun draw(){
        Painter.drawColor(color,x,y,width,heigth)
    }
    override fun willCollision(block:BlockAble): station? {
        //TODO:检测碰撞
        var x = this.x
        var y = this.y

        when(currentDirection){
            station.UP -> y-=5
            station.DOWN -> y+=5
            station.LEFT -> x-=5
            station.RIGHT -> x+=5
        }

        block as wall
        if (block.y+block.heigth<=y){
            return null
        }else if (y+this.heigth<=block.y){
            return null
        }else if (block.x+block.width<=x){
            return null
        }else if (x+this.width<=block.x){
            return null
        }else{
            return this.currentDirection
        }

    }
    override fun notifyblock(station: station?, block: BlockAble?) {
        //TODO:接受碰撞信息
        this.badstation = station
    }
}
enum class station{
    UP,DOWN,RIGHT,LEFT

}