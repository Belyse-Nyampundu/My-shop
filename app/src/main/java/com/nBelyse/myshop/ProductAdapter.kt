package com.nBelyse.myshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nBelyse.myshop.databinding.ProductListBinding
import com.nBelyse.myshop.model.Product
import com.nBelyse.myshop.ui.ProductDetails
import com.squareup.picasso.Picasso

class  ProductAdapter(var productList: List<Product>):RecyclerView.Adapter<ProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
   val binding = ProductListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       var fetchedProduct = productList[position]
        var binding=holder.binding
        binding.tvTitle.text=fetchedProduct.title
        binding.tvPrice.text=fetchedProduct.price.toString()

        Picasso.get().load(fetchedProduct.thumbnail).resize(500,400).centerCrop().into(binding.ivImage)
        holder.binding.cvProduct.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetails::class.java)
            intent.putExtra("PRODUCT_ID",fetchedProduct.id)
            holder.itemView.context.startActivity(intent)


        }

    }


}



class ProductViewHolder(var binding:ProductListBinding):RecyclerView.ViewHolder(binding.root){

}