package club.caohao.game.model

import club.caohao.game.service.BlockAble
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Painter


class wall(override var x:Int,override var y:Int) :obj,BlockAble{

    override var width: Int=50
    get
    override var heigth: Int=50
    get




    override fun draw(){
        Painter.drawColor(Color.YELLOW,x,y,width,heigth)
    }
}