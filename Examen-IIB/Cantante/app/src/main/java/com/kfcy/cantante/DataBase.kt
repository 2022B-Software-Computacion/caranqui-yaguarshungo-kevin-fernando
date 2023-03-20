package com.kfcy.cantante

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class DataBase {

    private val db = FirebaseFirestore.getInstance()
    private val singerRef = db.collection("singers")

    // Create a new singer
    fun createSinger(
        singer: Singer,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Add the singer to Firestore
        singerRef.add(singer)
            .addOnSuccessListener { documentReference ->
                // Get reference to the "songs" subcollection within each created singer
                val songsRef = documentReference.collection("songs")

                // Add each song to the "songs" subcollection within each singer
                for (song in singer.songs) {
                    songsRef.add(song)
                        .addOnSuccessListener {
                            song.id = it.id
                        }
                        .addOnFailureListener { onFailure(it) }
                }

                onSuccess(documentReference.id)
            }
            .addOnFailureListener { onFailure(it) }
    }

    // Update an existing singer
    fun updateSinger(
        singerId: String,
        singer: Singer,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        singerRef.document(singerId).set(singer)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    // Delete an existing singer
    fun deleteSinger(
        singerId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        singerRef.document(singerId).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    // Get all singers
    fun getSingers(onSuccess: (List<Singer>) -> Unit, onFailure: (Exception) -> Unit) {
        singerRef.get()
            .addOnSuccessListener { result ->
                val singers = mutableListOf<Singer>()
                for (document in result) {
                    val singer = document.toObject(Singer::class.java)
                    singer.id = document.id
                    getSongsOfSinger(singer.id!!, onSuccess = {
                        singer.songs = it
                    }, onFailure = {})
                    singers.add(singer)
                }
                onSuccess(singers)
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error getting singers", exception)
                onFailure(exception)
            }
    }

    // Get all songs of a singer
    fun getSongsOfSinger(
        singerId: String,
        onSuccess: (List<Song>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val songsRef = singerRef.document(singerId).collection("songs")

        songsRef.get()
            .addOnSuccessListener { result ->
                val songs = mutableListOf<Song>()
                for (document in result) {
                    val song = document.toObject(Song::class.java)
                    song.id = document.id
                    songs.add(song)
                }
                onSuccess(songs)
            }
            .addOnFailureListener { onFailure(it) }
    }

    // Update an existing song within a singer
    fun updateSong(singerId: String, song: Song, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val songsRef = singerRef.document(singerId).collection("songs")
        songsRef.document(song.id!!).set(song)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    // Update an existing song within a singer
    fun addSong(singerId: String, song: Song, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val songsRef = singerRef.document(singerId).collection("songs")
        songsRef.add(song)
            .addOnSuccessListener {
                song.id = it.id
                onSuccess()
            }
            .addOnFailureListener { onFailure(it) }
    }

    // Delete an existing song within a singer
    fun deleteSong(
        singerId: String,
        songId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Get reference to the song document within the subcollection
        val songRef =
            singerRef.document(singerId).collection("songs").document(songId)

        songRef.get()
            .addOnSuccessListener { songDoc ->
                // Verify that the document exists before deleting it
                if (songDoc.exists()) {
                    // Delete the document from the subcollection
                    songRef.delete()
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure(it) }
                } else {
                    onFailure(Exception("Song with ID $songId not found"))
                }
            }
            .addOnFailureListener { onFailure(it) }
    }



}