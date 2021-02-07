package facade


/**
 * @author ranaaditya
 */
class Amplifier(var description: String) {
    var mTuner: Tuner? = null
    var dvdPlayer: DvdPlayer? = null
    var cdPlayer: CdPlayer? = null
    fun on() {
        println("$description on")
    }

    fun off() {
        println("$description off")
    }

    fun setStereoSound() {
        println("$description stereo mode on")
    }

    fun setSurroundSound() {
        println("$description surround sound on (5 speakers, 1 subwoofer)")
    }

    fun setVolume(level: Int) {
        println("$description setting volume to $level")
    }

    fun setTuner(tuner: Tuner?) {
        println("$description setting tuner to $dvdPlayer")
        this.mTuner = tuner
    }

    fun setDvd(dvd: DvdPlayer) {
        println("$description setting DVD player to $dvd")
        this.dvdPlayer = dvd
    }

    fun setCd(cd: CdPlayer) {
        println("$description setting CD player to $cd")
        this.cdPlayer = cd
    }

    override fun toString(): String {
        return description
    }

}

class Tuner {
    fun on() {}
    fun off() {}
    fun setFrequency(frequency: Double) {}
}

class CdPlayer(var description: String, var amplifier: Amplifier) {
    var currentTrack = 0
    var title: String? = null
    fun on() {
        println("$description on")
    }

    fun off() {
        println("$description off")
    }

    fun eject() {
        title = null
        println("$description eject")
    }

    fun play(title: String) {
        this.title = title
        currentTrack = 0
        println("$description playing \"$title\"")
    }

    fun play(track: Int) {
        if (title == null) {
            println(description + " can't play track " + currentTrack +
                    ", no cd inserted")
        } else {
            currentTrack = track
            println("$description playing track $currentTrack")
        }
    }

    fun stop() {
        currentTrack = 0
        println("$description stopped")
    }

    fun pause() {
        println("$description paused \"$title\"")
    }

    override fun toString(): String {
        return description
    }

}

class DvdPlayer(var description: String, var amplifier: Amplifier) {
    var currentTrack = 0
    var movie: String? = null
    fun on() {
        println("$description on")
    }

    fun off() {
        println("$description off")
    }

    fun eject() {
        movie = null
        println("$description eject")
    }

    fun play(movie: String) {
        this.movie = movie
        currentTrack = 0
        println("$description playing \"$movie\"")
    }

    fun play(track: Int) {
        if (movie == null) {
            println("$description can't play track $track no dvd inserted")
        } else {
            currentTrack = track
            println("$description playing track $currentTrack of \"$movie\"")
        }
    }

    fun stop() {
        currentTrack = 0
        println("$description stopped \"$movie\"")
    }

    fun pause() {
        println("$description paused \"$movie\"")
    }

    fun setTwoChannelAudio() {
        println("$description set two channel audio")
    }

    fun setSurroundAudio() {
        println("$description set surround audio")
    }

    override fun toString(): String {
        return description
    }

}

class Projector(var description: String, var dvdPlayer: DvdPlayer) {
    fun on() {
        println("$description on")
    }

    fun off() {
        println("$description off")
    }

    fun wideScreenMode() {
        println("$description in widescreen mode (16x9 aspect ratio)")
    }

    fun tvMode() {
        println("$description in tv mode (4x3 aspect ratio)")
    }

    override fun toString(): String {
        return description
    }

}

class TheaterLights(var description: String) {
    fun on() {
        println("$description on")
    }

    fun off() {
        println("$description off")
    }

    fun dim(level: Int) {
        println("$description dimming to $level%")
    }

    override fun toString(): String {
        return description
    }

}

class Screen(var description: String) {
    fun up() {
        println("$description going up")
    }

    fun down() {
        println("$description going down")
    }

    override fun toString(): String {
        return description
    }

}

class PopcornPopper(var description: String) {
    fun on() {
        println("$description on")
    }

    fun off() {
        println("$description off")
    }

    fun pop() {
        println("$description popping popcorn!")
    }

    override fun toString(): String {
        return description
    }

}


class HomeTheaterFacade(var amp: Amplifier,
                        tuner: Tuner,
                        dvd: DvdPlayer,
                        cd: CdPlayer,
                        projector: Projector,
                        screen: Screen,
                        lights: TheaterLights,
                        popper: PopcornPopper) {
    var tuner: Tuner
    var dvd: DvdPlayer
    var cd: CdPlayer
    var projector: Projector
    var lights: TheaterLights
    var screen: Screen
    var popper: PopcornPopper
    fun watchMovie(movie: String?) {
        println("Get ready to watch a movie...")
        popper.on()
        popper.pop()
        lights.dim(10)
        screen.down()
        projector.on()
        projector.wideScreenMode()
        amp.on()
        amp.setDvd(dvd)
        amp.setSurroundSound()
        amp.setVolume(5)
        dvd.on()
        dvd.play(movie!!)
    }

    fun endMovie() {
        println("Shutting movie theater down...")
        popper.off()
        lights.on()
        screen.up()
        projector.off()
        amp.off()
        dvd.stop()
        dvd.eject()
        dvd.off()
    }

    fun listenToCd(cdTitle: String?) {
        println("Get ready for an audiopile experence...")
        lights.on()
        amp.on()
        amp.setVolume(5)
        amp.setCd(cd)
        amp.setStereoSound()
        cd.on()
        cd.play(cdTitle!!)
    }

    fun endCd() {
        println("Shutting down CD...")
        amp.off()
        amp.setCd(cd)
        cd.eject()
        cd.off()
    }

    fun listenToRadio(frequency: Double) {
        println("Tuning in the airwaves...")
        tuner.on()
        tuner.setFrequency(frequency)
        amp.on()
        amp.setVolume(5)
        amp.setTuner(tuner)
    }

    fun endRadio() {
        println("Shutting down the tuner...")
        tuner.off()
        amp.off()
    }

    init {
        this.tuner = tuner
        this.dvd = dvd
        this.cd = cd
        this.projector = projector
        this.screen = screen
        this.lights = lights
        this.popper = popper
    }
}

