package adapter

interface MediaPlayer {
    public fun play(audioType: String, fileName: String)
}

interface AdvancedMediaPlayer {
    public fun playVLC(fileName: String)
    public fun playMP4(fileName: String)
}

open class VLCPlayer : AdvancedMediaPlayer {
    override fun playVLC(fileName: String) {
        println("Playing VLC file : $fileName")
    }

    override fun playMP4(fileName: String) {
        // do nothing here
    }
}

open class MP4Player : AdvancedMediaPlayer {
    override fun playVLC(fileName: String) {
        // do nothing here
    }

    override fun playMP4(fileName: String) {
        println("Playing MP4 file : $fileName")
    }
}

open class MediaAdapter(audioType: String) : MediaPlayer {

    private lateinit var advancedMediaPlayer: AdvancedMediaPlayer

    init {
        if (audioType == "VLC") {
            advancedMediaPlayer = VLCPlayer()
        } else if (audioType == "MP4") {
            advancedMediaPlayer = MP4Player()
        }
    }

    override fun play(audioType: String, fileName: String) {
        if (audioType == "VLC") {
            advancedMediaPlayer.playVLC(fileName)
        } else if (audioType == "MP4") {
            advancedMediaPlayer.playMP4(fileName)
        }
    }
}

open class AudioPlayer : MediaPlayer {

    private lateinit var mediaAdapter: MediaAdapter

    override fun play(audioType: String, fileName: String) {
        if (audioType == "MP3") {
            println("Playing MP3 file: $fileName")
        } else if (audioType == "MP4" || audioType == "VLC") {
            mediaAdapter = MediaAdapter(audioType)
            mediaAdapter.play(audioType, fileName)
        } else {
            println("Invalid media type. $audioType not supported.")
        }
    }
}

public open class AdapterPattern {
    private var audioPlayer = AudioPlayer()

    fun main() {
        audioPlayer.play("MP3", "beyond the horizon.mp3")
        audioPlayer.play("MP4", "alone.mp4")
        audioPlayer.play("VLC", "far far away.vlc")
        audioPlayer.play("AVI", "mind me.avi")
    }
}