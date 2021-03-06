package hu.ait.androidfinal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.*
import hu.ait.androidfinal.data.*

class RecipeViewModel : ViewModel() {

    companion object {
        const val TAG = "Recipe_VIEW_MODEL"
    }
    private var favoritesRepository = FavoritesRepository()
    private var pantryRepository = PantryRepository()
    private val savedFavorites : MutableLiveData<List<Meal>> = MutableLiveData()


    // save favorite to firebase
    fun saveFavoriteToRepo(favorite: Meal) {
        favoritesRepository.saveFavorite(favorite).addOnFailureListener {
            Log.e(TAG, "Failed to save Recipe!")
        }
    }

    // get realtime updates from firebase regarding saved favorites
    fun getSavedFavorites(): MutableLiveData<List<Meal>>{
        favoritesRepository.getSavedFavorites().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedFavorites.value = null
                return@EventListener
            }

            val savedFavoritesList : MutableList<Meal> = mutableListOf()
            for (doc in value!!) {
                val favoriteItem = doc.toObject(Meal::class.java)
                savedFavoritesList.add(favoriteItem)
            }
            savedFavorites.value = savedFavoritesList

        })

        return savedFavorites
    }

    fun deleteFavorite(favoriteItem: Meal) {
        favoritesRepository.deleteFavorite(favoriteItem).addOnFailureListener {
            Log.e(TAG, "Failed to delete Recipe")
        }
    }

    fun saveItemToPantry(item: Ingredient){
        pantryRepository.saveItem(item).addOnFailureListener {
            Log.e(TAG, "Failed to save Recipe!")
        }
    }


    fun instructionsList(recipe: Meal, ingredientList : MutableList<String>, amountList:MutableList<String>):List<Pair<String, String>>{
        if (!(recipe.strIngredient20.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient20)
            amountList.add(recipe.strMeasure20!!)
        }
        if (!(recipe.strIngredient19.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient19)
            amountList.add(recipe.strMeasure19!!)
        }
        if (!(recipe.strIngredient18.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient18)
            amountList.add(recipe.strMeasure18!!)
        }
        if (!(recipe.strIngredient17.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient17)
            amountList.add(recipe.strMeasure17!!)
        }
        if (!(recipe.strIngredient16.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient16)
            amountList.add(recipe.strMeasure16!!)
        }
        if (!(recipe.strIngredient15.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient15)
            amountList.add(recipe.strMeasure15!!)
        }
        if (!(recipe.strIngredient14.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient14)
            amountList.add(recipe.strMeasure14!!)
        }
        if (!(recipe.strIngredient13.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient13)
            amountList.add(recipe.strMeasure13!!)
        }
        if (!(recipe.strIngredient12.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient12)
            amountList.add(recipe.strMeasure12!!)
        }
        if (!(recipe.strIngredient11.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient11)
            amountList.add(recipe.strMeasure11!!)
        }
        if (!(recipe.strIngredient10.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient10)
            amountList.add(recipe.strMeasure10!!)
        }
        if (!(recipe.strIngredient9.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient9)
            amountList.add(recipe.strMeasure9!!)
        }
        if (!(recipe.strIngredient8.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient8)
            amountList.add(recipe.strMeasure8!!)
        }
        if (!(recipe.strIngredient7.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient7)
            amountList.add(recipe.strMeasure7!!)
        }
        if (!(recipe.strIngredient6.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient6)
            amountList.add(recipe.strMeasure6!!)
        }
        if (!(recipe.strIngredient5.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient5)
            amountList.add(recipe.strMeasure5!!)
        }
        if (!(recipe.strIngredient4.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient4)
            amountList.add(recipe.strMeasure4!!)
        }
        if (!(recipe.strIngredient3.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient3)
            amountList.add(recipe.strMeasure3!!)
        }
        if (!(recipe.strIngredient2.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient2)
            amountList.add(recipe.strMeasure2!!)
        }
        if (!(recipe.strIngredient1.isNullOrBlank())) {
            ingredientList.add(recipe.strIngredient1)
            amountList.add(recipe.strMeasure1!!)
        }

        return ingredientList.zip(amountList)

    }

    fun updateIsChecked(item: Ingredient){
        pantryRepository.updateIsChecked(item).addOnFailureListener {
            Log.d(TAG, "Failed to Update Include")
        }
    }


    fun getIncludedString(includedList : MutableList<String>) : String {
        val formattedList : MutableList<String> = mutableListOf()
        for (item in includedList){
            val name = item.replace(" ", "_")
            formattedList.add(name)
        }
        return formattedList.joinToString(",")
    }
}

