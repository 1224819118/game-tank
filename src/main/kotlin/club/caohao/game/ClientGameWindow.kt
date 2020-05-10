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
import util.ClientMessageUtil
import util.MessageUtil
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
            KeyCode.W -> {MessageUtil.queue.add("UP")
                t2.move(station.UP) }
            KeyCode.S ->{MessageUtil.queue.add("DOWN")
                t2.move(station.DOWN)}
            KeyCode.A ->{MessageUtil.queue.add("LEFT")
                t2.move(station.LEFT)}
            KeyCode.D ->{MessageUtil.queue.add("RIGHT")
                t2.move(station.RIGHT)}
            KeyCode.ENTER -> {walls.add(t2.sendBot())}
        }
    }

    fun onKeyPressed2(move: station){
       t1.move(move)
    }

    override fun onRefresh() {
        walls.filter { it is Moveable }.forEach movetag@{move ->
            move as Moveable
            var blockstation:station? = null
            var blockable:BlockAble? = null
            walls.filter { it is BlockAble }.forEach blocktag@{block ->
                block as BlockAble
                var willCollision = move.willCollision(block)
                willCollision?.let {
                    blockstation = willCollision
                    blockable = block
                    return@blocktag
                }
            }
            //找到碰撞物体了
            move.notifyblock(blockstation,blockable)
        }


        

    }

    fun otherOneMove(){
        var peek = ClientMessageUtil.queue.poll()
        when(peek){
            "UP" -> t1.move(station.UP)
            "DOWN" -> t1.move(station.DOWN)
            "LEFT" -> t1.move(station.LEFT)
            "RIGHT" -> t1.move(station.RIGHT)
        }
    }


}



