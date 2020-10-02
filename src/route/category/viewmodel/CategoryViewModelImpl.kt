package route.category.viewmodel

import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import domain.category.CategoryUseCase
import domain.category.model.Category
import io.reactivex.disposables.CompositeDisposable
import utils.rx.ext.addTo

class CategoryViewModelImpl(
    private val categoryUseCase: CategoryUseCase,
    private val compositeDisposable: CompositeDisposable
): CategoryViewModel {

    override fun listCategory(listCategoryRequest: ListCategoryRequest): List<Category> {
        var result: List<Category> = arrayListOf()
        categoryUseCase.listCategory(listCategoryRequest)
            .subscribe({
                result = it
            },{
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun getCategory(id: Int): Category {
        var result = Category()
        categoryUseCase.getCategory(id)
            .subscribe({
                result = it
            },{
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Boolean {
        var result  = false
        categoryUseCase.insertCategory(insertCategoryRequest)
            .subscribe({
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Boolean {
        var result  = false
        categoryUseCase.updateCategory(updateCategoryRequest)
            .subscribe({
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun deleteCategory(id: Int): Boolean {
        var result  = false
        categoryUseCase.deleteCategory(id)
            .subscribe({
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

}