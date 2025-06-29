package com.example.dictionary

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var wordList: ArrayList<Word>
    private lateinit var wordAdapter: WordAdapter
    private lateinit var db: DatabaseHelper
    private lateinit var searchView: SearchView
    private var isEnglish = true
    private var isTurkish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        copyDatabase()

        binding.toolbar.title = "Dictionary"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        db = DatabaseHelper(this)

        wordList = WordDao().allWords(db)

        binding.checkBoxEn.setOnCheckedChangeListener { _, isChecked ->
            isEnglish = isChecked
            val currentQuery = searchView.query.toString()
            searchWord(currentQuery)
        }
        binding.checkBoxTr.setOnCheckedChangeListener { _, isChecked ->
            isTurkish = isChecked
            val currentQuery = searchView.query.toString()
            searchWord(currentQuery)
        }

        wordAdapter = WordAdapter(this, wordList)
        binding.rv.adapter = wordAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return true
//        return super.onCreateOptionsMenu(menu)
    }

    // it runs when text is submitted
    override fun onQueryTextSubmit(query: String?): Boolean {
        searchWord(query.orEmpty())
        Log.d("onQueryTextSubmit", query.toString())
        return true
    }

    // it runs when text is changed
    override fun onQueryTextChange(newText: String?): Boolean {
        searchWord(newText.orEmpty())
        Log.d("onQueryTextChange", newText.toString())
        return true
    }

    private fun copyDatabase() {
        val dbCopyHelper = DatabaseCopyHelper(this)

        try {
            dbCopyHelper.createDataBase()
            dbCopyHelper.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun searchWord(word: String) {
        wordList = WordDao().searchWord(db, word, isEnglish, isTurkish)
        wordAdapter = WordAdapter(this, wordList)
        binding.rv.adapter = wordAdapter
    }
}