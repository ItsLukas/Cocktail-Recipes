package com.example.cocktailRecipes.data.database.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail", indices = [Index(value = ["idDrink"], unique = true)])
data class Drink(
    @PrimaryKey(autoGenerate = false)
    val idDrink: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strGlass: String,
    val strInstructions: String,
    @ColumnInfo(name = "strIngredient1", defaultValue = "") val strIngredient1: String,
    @ColumnInfo(name = "strIngredient2", defaultValue = "") val strIngredient2: String,
    @ColumnInfo(name = "strIngredient3", defaultValue = "") val strIngredient3: String,
    @ColumnInfo(name = "strIngredient4", defaultValue = "") val strIngredient4: String,
    @ColumnInfo(name = "strIngredient5", defaultValue = "") val strIngredient5: String,
    @ColumnInfo(name = "strIngredient6", defaultValue = "") val strIngredient6: String,
    @ColumnInfo(name = "strIngredient7", defaultValue = "") val strIngredient7: String,
    @ColumnInfo(name = "strIngredient8", defaultValue = "") val strIngredient8: String,
    @ColumnInfo(name = "strIngredient9", defaultValue = "") val strIngredient9: String,
    @ColumnInfo(name = "strIngredient10", defaultValue = "") val strIngredient10: String,
    @ColumnInfo(name = "strIngredient11", defaultValue = "") val strIngredient11: String,
    @ColumnInfo(name = "strIngredient12", defaultValue = "") val strIngredient12: String,
    @ColumnInfo(name = "strIngredient13", defaultValue = "") val strIngredient13: String,
    @ColumnInfo(name = "strIngredient14", defaultValue = "") val strIngredient14: String,
    @ColumnInfo(name = "strIngredient15", defaultValue = "") val strIngredient15: String,
    @ColumnInfo(name = "strMeasure1", defaultValue = "") val strMeasure1: String,
    @ColumnInfo(name = "strMeasure2", defaultValue = "") val strMeasure2: String,
    @ColumnInfo(name = "strMeasure3", defaultValue = "") val strMeasure3: String,
    @ColumnInfo(name = "strMeasure4", defaultValue = "") val strMeasure4: String,
    @ColumnInfo(name = "strMeasure5", defaultValue = "") val strMeasure5: String,
    @ColumnInfo(name = "strMeasure6", defaultValue = "") val strMeasure6: String,
    @ColumnInfo(name = "strMeasure7", defaultValue = "") val strMeasure7: String,
    @ColumnInfo(name = "strMeasure8", defaultValue = "") val strMeasure8: String,
    @ColumnInfo(name = "strMeasure9", defaultValue = "") val strMeasure9: String,
    @ColumnInfo(name = "strMeasure10", defaultValue = "") val strMeasure10: String,
    @ColumnInfo(name = "strMeasure11", defaultValue = "") val strMeasure11: String,
    @ColumnInfo(name = "strMeasure12", defaultValue = "") val strMeasure12: String,
    @ColumnInfo(name = "strMeasure13", defaultValue = "") val strMeasure13: String,
    @ColumnInfo(name = "strMeasure14", defaultValue = "") val strMeasure14: String,
    @ColumnInfo(name = "strMeasure15", defaultValue = "") val strMeasure15: String,
    @ColumnInfo(name = "dateModified", defaultValue = "") val dateModified: String
)