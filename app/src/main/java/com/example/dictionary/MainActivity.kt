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

        binding.toolbar.title = "Dictionary"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        wordList = ArrayList()

        wordList.add(Word(1, "Hello", "Merhaba"))
        wordList.add(Word(2, "Yes", "Evet"))
        wordList.add(Word(3, "No", "Hayır"))
        wordList.add(Word(4, "Good", "İyi"))
        wordList.add(Word(5, "Bad", "Kötü"))
        wordList.add(Word(6, "Thank you", "Teşekkürler"))
        wordList.add(Word(7, "Goodbye", "Görüşmek üzere"))
        wordList.add(Word(8, "See you", "Görüşürüz"))
        wordList.add(Word(9, "Please", "Lütfen"))
        wordList.add(Word(10, "Car", "Araba"))
        wordList.add(Word(11, "Bus", "Otobüs"))
        wordList.add(Word(12, "Bicycle", "Tren"))
        wordList.add(Word(13, "Motorcycle", "Motosiklet"))
        wordList.add(Word(14, "Train", "Tren"))
        wordList.add(Word(15, "Taxi", "Taksi"))
        wordList.add(Word(16, "Plane", "Uçak"))
        wordList.add(Word(17, "Computer", "Bilgisayar"))
        wordList.add(Word(18, "Phone", "Telefon"))
        wordList.add(Word(19, "Table", "Masa"))
        wordList.add(Word(20, "Chair", "Sırt"))
        wordList.add(Word(21, "Book", "Kitap"))
        wordList.add(Word(22, "Pen", "Kalem"))
        wordList.add(Word(23, "Pencil", "Kalem"))
        wordList.add(Word(24, "Notebook", "Defter"))
        wordList.add(Word(25, "Laptop", "Laptop"))
        wordList.add(Word(26, "Mouse", "Mause"))
        wordList.add(Word(27, "Keyboard", "Klavye"))
        wordList.add(Word(28, "Monitor", "Ekran"))
        wordList.add(Word(29, "Earphones", "Kulaklık"))
        wordList.add(Word(30, "Mobile phone", "Cep telefonu"))
        wordList.add(Word(31, "Headphones", "Kulaklık"))

        wordAdapter = WordAdapter(this, wordList)
        binding.rv.adapter = wordAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    // it runs when text is submitted
    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("onQueryTextSubmit", query.toString())
        return true
    }

    // it runs when text is changed
    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("onQueryTextChange", newText.toString())
        return true
    }
}