package com.example.dictionary

class WordDao {

    fun allWords(dbHelper: DatabaseHelper): ArrayList<Word> {
        val wordList = ArrayList<Word>()
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM dictionary", null)
        while (cursor.moveToNext()) {
            val word = Word(
                cursor.getColumnIndex("id"),
                cursor.getString(cursor.getColumnIndex("english")),
                cursor.getString(cursor.getColumnIndex("turkish"))
            )
            wordList.add(word)
        }
        cursor.close()
        db.close()
        return wordList
    }

    fun searchWord(
        dbHelper: DatabaseHelper,
        word: String,
        isEnglish: Boolean,
        isTurkish: Boolean
    ): ArrayList<Word> {
        val wordList = ArrayList<Word>()
        val db = dbHelper.writableDatabase

        val selection = when {
            isEnglish && isTurkish -> "english LIKE ? OR turkish LIKE ?"
            isEnglish -> "english LIKE ?"
            isTurkish -> "turkish LIKE ?"
            else -> return wordList
        }

        val args = when {
            isEnglish && isTurkish -> arrayOf("%$word%", "%$word%")
            else -> arrayOf("%$word%")
        }

        val cursor = db.rawQuery("SELECT * FROM dictionary WHERE $selection", args)

        while (cursor.moveToNext()) {
            val word = Word(
                cursor.getColumnIndex("id"),
                cursor.getString(cursor.getColumnIndex("english")),
                cursor.getString(cursor.getColumnIndex("turkish"))
            )
            wordList.add(word)
        }
        cursor.close()
        db.close()
        return wordList
    }
}