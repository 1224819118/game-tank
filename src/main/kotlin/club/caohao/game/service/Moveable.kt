package club.caohao.game.service

import club.caohao.game.model.station

interface Moveable {
    /**
     * 判断移动物体和阻塞物体发生碰撞
     *
     * @return 要碰撞的方向  如果为空则是说明没有碰撞
     */
    fun willCollision(block:BlockAble):station?

    fun notifyblock(station: station?,block: BlockAble?)
}