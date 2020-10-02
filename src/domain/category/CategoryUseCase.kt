package domain.category

import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import domain.category.model.Category
import io.reactivex.Flowable
import io.reactivex.Single

interface CategoryUseCase {

    fun listCategory(listCategoryRequest: ListCategoryRequest): Flowable<List<Category>>

    fun getCategory(id: Int): Single<Category>

    fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Single<Boolean>

    fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Single<Boolean>

    fun deleteCategory(id: Int): Single<Boolean>

}