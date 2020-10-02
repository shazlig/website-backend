package domain.category

import data.category.CategoryRepository
import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import domain.category.model.Category
import domain.category.model.toObject
import io.reactivex.Flowable
import io.reactivex.Single

class CategoryInteractor(private val categoryRepository: CategoryRepository): CategoryUseCase {

    override fun listCategory(listCategoryRequest: ListCategoryRequest): Flowable<List<Category>> {
        return categoryRepository.listCategory(listCategoryRequest).map { list ->
            list.map { it.toObject() }
        }
    }

    override fun getCategory(id: Int): Single<Category> {
        return categoryRepository.getCategory(id).map {
            it.toObject()
        }
    }

    override fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Single<Boolean> {
        return categoryRepository.insertCategory(insertCategoryRequest)
    }

    override fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Single<Boolean> {
        return categoryRepository.updateCategory(updateCategoryRequest)
    }

    override fun deleteCategory(id: Int): Single<Boolean> {
        return categoryRepository.deleteCategory(id)
    }
}