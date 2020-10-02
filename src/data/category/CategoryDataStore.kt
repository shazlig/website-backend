package data.category

import data.category.db.CategoryDatabase
import data.category.model.CategoryItem
import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import io.reactivex.Flowable
import io.reactivex.Single

class CategoryDataStore(private val categoryDatabase: CategoryDatabase) : CategoryRepository {

    override fun listCategory(listCategoryRequest: ListCategoryRequest): Flowable<List<CategoryItem>> {
        return categoryDatabase.listCategory(listCategoryRequest).map {
            it
        }
    }

    override fun getCategory(id: Int): Single<CategoryItem> {
        return categoryDatabase.getCategory(id).map {
            it
        }
    }

    override fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Single<Boolean> {
        return categoryDatabase.insertCategory(insertCategoryRequest)
    }

    override fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Single<Boolean> {
        return categoryDatabase.updateCategory(updateCategoryRequest)
    }

    override fun deleteCategory(id: Int): Single<Boolean> {
        return categoryDatabase.deleteCategory(id)
    }
}