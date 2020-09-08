package pakultie.julxian.simpleproject.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM DataItems")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM DataItems WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Character>

    @Query("DELETE  FROM DataItems ")
    fun deleteDB()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(character: Character)
}