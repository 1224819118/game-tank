package club.caohao.game

import club.caohao.game.model.obj
import club.caohao.game.model.station
import club.caohao.game.model.tank
import club.caohao.game.model.wall
import club.caohao.game.service.BlockAble
import club.caohao.game.service.Moveable
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import java.io.File


class ClientGameWindow :Window("坦克大战","img/icon.png",1000,1000) {
    var t1:tank = tank(Color.GREEN,50,50)
    var t2:tank = tank(Color.BLUE,950,50)

    private var walls = arrayListOf<obj>()

    override fun onCreate() {
        //绘制地图
        var file = File(javaClass.getResource("/map/map1.txt").path)
        var linenum =0
        var lines:List<String> = file.readLines()
        lines.forEach {
            var colunmnum = 0
            it.toCharArray().forEach {
                if (it == '1'){
                    var wall = wall(colunmnum * 50, linenum * 50)
                    walls.add(wall)
                }
                colunmnum ++
            }
            linenum ++
        }
        walls.add(t1)
        walls.add(t2)

    }

    override fun onDisplay() {

        walls.forEach {
            it.draw()
        }


    }

    override fun onKeyPressed(event: KeyEvent) {
        //检测人物的操作
        when(event.code){
            KeyCode.W -> t2.move(station.UP)
            KeyCode.S -> t2.move(station.DOWN)
            KeyCode.A -> t2.move(station.LEFT)
            KeyCode.D -> t2.move(station.RIGHT)
        }
    }

    fun onKeyPressed2(move: station){
       t1.move(move)
    }

    override fun onRefresh() {
        walls.filter { it is Moveable }.forEach {move ->
            walls.filter { it is BlockAble }.forEach{block ->

            }
        }


        

    }

    fun Painter.drawObj(obj: obj){
        //Painter.drawColor(obj.color,obj.x,obj.y,obj.width,obj.heigth)
        Painter.drawColor(Color.GREEN,50,50,40,40)
    }


}



