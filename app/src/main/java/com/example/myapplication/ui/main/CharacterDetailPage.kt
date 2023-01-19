package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.api.models.CharacterData
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.util.jar.Manifest

class CharacterDetailPage: Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(CharacterDetailViewModel::class.java) }
    private val REQUEST_CODE_STORAGE_PERMISSION = 1
    private val character = MutableLiveData<CharacterData>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { val view = inflater.inflate(R.layout.fragment_character_details_page, container, false)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val textViewName: TextView = view.findViewById(R.id.text_view_name)
        val imagePicture: ImageView = view.findViewById(R.id.image)
        val textViewStatus: TextView = view.findViewById(R.id.text_view_status)
        val textViewSpecies: TextView = view.findViewById(R.id.text_view_species)
        val textViewOrigin: TextView = view.findViewById(R.id.text_view_origin)
        val exportButton: Button = view.findViewById(R.id.button_transport_file)

        arguments?.let { viewModel.refreshData(characterId = it.getInt("CHARACTER_ID")) }

        viewModel.characters.observe(viewLifecycleOwner, Observer { characterData ->
            Picasso.get().load(characterData.image).into(imagePicture)
            textViewName.text = "Name: ${characterData.name}"
            textViewStatus.text = "Status: ${characterData.status}"
            textViewSpecies.text = "Species: ${characterData.species}"
            textViewOrigin.text = "Origin: ${characterData.origin.name}"


            exportButton.setOnClickListener {
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_STORAGE_PERMISSION
                    )
                } else{
                    exportCharacterDetails()
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
        })

        return view
    }

    private fun exportCharacterDetails() {
        try {
            val file = File(Environment.getExternalStorageDirectory(), "character_details.txt")
            val outputStream = FileOutputStream(file)

            val data = "Name: ${character.value?.name}\nStatus: ${character.value?.status}" +
                    "\nSpecies: ${character.value?.species}\nOrigin: ${character.value?.origin?.name}"
            outputStream.write(data.toByteArray())
            outputStream.close()

            Toast.makeText(context, "File exported to ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            exportCharacterDetails()
        } else {
        Toast.makeText(context,
            "Storage permission is required to export the file", Toast.LENGTH_LONG).show()
    }
    }

//    @Deprecated("Deprecated in Java")
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            REQUEST_CODE_STORAGE_PERMISSION -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    exportCharacterDetails()
//                } else {
//                    Toast.makeText(context,
//                        "Storage permission is required to export the file", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }
}



