package route.category.viewmodel

import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import domain.category.model.Category

interface CategoryViewModel {

    fun listCategory(listCategoryRequest: ListCategoryRequest): List<Category>

    fun getCategory(id: Int): Category

    fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Boolean

    fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Boolean

    fun deleteCategory(id: Int): Boolean

}