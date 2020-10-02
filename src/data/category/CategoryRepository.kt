package data.category

import data.category.model.CategoryItem
import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import io.reactivex.Flowable
import io.reactivex.Single

interface CategoryRepository {

    fun listCategory(listCategoryRequest: ListCategoryRequest): Flowable<List<CategoryItem>>

    fun getCategory(id: Int): Single<CategoryItem>

    fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Single<Boolean>

    fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Single<Boolean>

    fun deleteCategory(id: Int): Single<Boolean>

}