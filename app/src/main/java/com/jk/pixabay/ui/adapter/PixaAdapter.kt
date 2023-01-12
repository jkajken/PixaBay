package com.jk.pixabay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jk.pixabay.databinding.ItemPixaBinding
import com.jk.pixabay.model.ImageModel

class PixaAdapter(private var imageList: ArrayList<ImageModel>) :
    RecyclerView.Adapter<PixaAdapter.PixaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemPixaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class PixaViewHolder(var binding: ItemPixaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ImageModel) {
            binding.pixaImgView.load(model.largeImageURL)
        }

    }}
