import javafx.application.Application
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window

class MyWindow :Window() {
    override fun onCreate() {

    }

    override fun onDisplay() {

    }

    override fun onKeyPressed(event: KeyEvent) {

    }

    override fun onRefresh() {

    }

}

fun main() {
    Application.launch(MyWindow::class.java)
}