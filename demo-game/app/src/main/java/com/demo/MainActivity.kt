package com.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.demo.game.model.Question
import com.demo.game.model.Score
import com.demo.game.viewmodel.CocktailsGameViewModel
import com.demo.game.viewmodel.CocktailsGameViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_loading.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CocktailsGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = (application as DemoApplication).repository
        val factory = (application as DemoApplication).gameFactory
        viewModel = ViewModelProvider(this,
            CocktailsGameViewModelFactory(repository, factory))
            .get(CocktailsGameViewModel::class.java)

        viewModel.getLoading().observe(this, Observer {
            it?.let { loading -> showLoading(loading) }
        })
        viewModel.getError().observe(this, Observer {
            it?.let { error -> showError(error) }
        })
        viewModel.getQuestion().observe(this, Observer {
            showQuestion(it)
        })
        viewModel.getScore().observe(this, Observer {
            it?.let { score -> showScore(score) }
        })

        nextButton.setOnClickListener { viewModel.nextQuestion() }

        viewModel.initGame()
    }

    private fun showScore(score: Score) {
        scoreTextView.text = getString(R.string.game_score, score.current)
        highScoreTextView.text = getString(R.string.game_highscore, score.highest)
    }

    private fun showQuestion(question: Question?) {
        if (question != null) {
            mainGroup.visibility = View.VISIBLE

            if (question.answeredOption != null) {
                showAnsweredQuestion(question)
            } else {
                showUnansweredQuestion(question)
            }
        } else {
            mainGroup.visibility = View.GONE
            noResultsTextView.visibility = View.VISIBLE
            questionResultImageView.visibility = View.GONE
        }
    }

    private fun showUnansweredQuestion(question: Question) {
        val options = question.getOptions()
        firstOptionButton.text = options[0]
        firstOptionButton.setOnClickListener {
            viewModel.answerQuestion(
                question,
                firstOptionButton.text.toString()
            )
        }
        firstOptionButton.isEnabled = true
        secondOptionButton.text = options[1]
        secondOptionButton.setOnClickListener {
            viewModel.answerQuestion(
                question,
                secondOptionButton.text.toString()
            )
        }
        secondOptionButton.isEnabled = true

        Glide.with(cocktailImageView).load(question.imageUrl).into(cocktailImageView)
        questionResultImageView.visibility = View.GONE
    }

    private fun showAnsweredQuestion(question: Question) {
        firstOptionButton.setOnClickListener(null)
        firstOptionButton.isEnabled = false
        secondOptionButton.setOnClickListener(null)
        secondOptionButton.isEnabled = false
        if (question.isAnsweredCorrectly) {
            questionResultImageView.setImageResource(R.drawable.ic_check_24dp)
        } else {
            questionResultImageView.setImageResource(R.drawable.ic_error_24dp)
        }
        questionResultImageView.visibility = View.VISIBLE
    }

    private fun showError(show: Boolean) {
        if (show) {
            errorContainer.visibility = View.VISIBLE
            retryButton.setOnClickListener { viewModel.initGame() }
        } else {
            errorContainer.visibility = View.GONE
        }
    }

    private fun showLoading(show: Boolean) {
        loadingContainer.visibility = if (show) View.VISIBLE else View.GONE
        mainGroup.visibility = View.GONE
        noResultsTextView.visibility = View.GONE
        questionResultImageView.visibility = View.GONE
    }
}