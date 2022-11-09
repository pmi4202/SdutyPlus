package com.d205.sdutyplus.view.timer.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.d205.sdutyplus.R
import com.d205.sdutyplus.databinding.DialogTaskRegistBinding
import com.d205.sdutyplus.uitls.getDeviceSize

import com.d205.sdutyplus.view.MainViewModel
import com.d205.sdutyplus.view.timer.viewmodel.TimerViewModel
import java.util.*

private const val TAG = "TaskDialog"

class TaskRegistDialog : DialogFragment() {
    private lateinit var binding: DialogTaskRegistBinding
    private val timerViewModel: TimerViewModel by activityViewModels()

    private val contentViews: List<ConstraintLayout> by lazy {
        listOf(binding.clContent1, binding.clContent2, binding.clContent3)
    }

    private val contentEditTexts: List<EditText> by lazy {
        listOf(binding.etContent1, binding.etContent2, binding.etContent3)
    }

    private val removeContentBtns: List<ImageView> by lazy {
        listOf(binding.ivRemoveContent1, binding.ivRemoveContent2, binding.ivRemoveContent3)
    }

    private val contentStrings: MutableList<String> by lazy {
        mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogTaskRegistBinding.inflate(inflater, container, false)

        initDialogWindow()

        return binding.root
    }

    private fun initDialogWindow() {
        dialog?.window?.apply{
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }
        // 여백 클릭 종료 불가 설정
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {

        setCurrentStudyTime()

        initBtn()

    }

    private fun setCurrentStudyTime() {
        setTimerTime(timerViewModel.timerTime.value!!)
    }

    private fun setTimerTime(time: Int) {
        val hour = time / 60 / 60
        val min = (time / 60) % 60
        val sec = time % 60
        binding.tvTimer.text = String.format("%02d:%02d:%02d", hour, min, sec)
    }

    private fun initBtn() {
        binding.apply {
            btnDelete.setOnClickListener {
                timerViewModel.timerTimeReset()
                dismiss()
            }

            ivAddContent.setOnClickListener {
                val nextContent: View  = getNextInVisibleContentView()
                if(nextContent is ConstraintLayout) {
                    nextContent.visibility = View.VISIBLE
                }
            }

//            btnSave.setOnClickListener {
//                // 등록 수행
//                val startTime = timerViewModel.startTime
//                val endTime = timerViewModel.endTime
//                val title = etTitle.text.toString()
//                val content1 = etContent1.text.toString()
//                val content2 = etContent2.text.toString()
//                val content3 = etContent3.text.toString()
//
//                val today = convertTimeDateToString(Date(System.currentTimeMillis()), "yyyy-MM-dd")
//
//                val newTask =
//                    Task(-1, -1, endTime, startTime, 0, title, content1, content2, content3)
//
//                val report = Report(-1, mainViewModel.user.value!!.seq, today, "", listOf(newTask))
//
//                // title 은 필수로 입력해야한다.
//                if (title.isNotEmpty()) {
//                    timerViewModel.saveTask(report)
//                    timerViewModel.getTodayReport(mainViewModel.user.value!!.seq)
//                    dismiss()
//                } else {
//                    ConfirmDialog().apply {
//                        // 경고창에 출력할 메시지를 담아 보낸다.
//                        arguments = Bundle().apply {
//                            putString("Action", "Error")
//                            putString("Message", "제목을 입력해주세요!!")
//                        }
//                        show(
//                            this@TaskRegistDialog.requireActivity().supportFragmentManager,
//                            "ConfirmDialog"
//                        )
//                    }
//                }
//            }

        }

        initRemoveContextBtns()
    }

    private fun getNextInVisibleContentView(): View {
        for((index, contentView) in contentViews.withIndex()) {
            if(contentView.visibility == View.GONE) {
                if(index == contentViews.size -1) {
                    binding.ivAddContent.visibility = View.GONE
                }
                return contentView
            }
        }
        return View(requireContext())
    }

    private fun initRemoveContextBtns() {
        for((index, removeBtn) in removeContentBtns.withIndex()) {
            removeBtn.setOnClickListener {

                // visible 인 content의 내용들을 저장
                for((index,contentView) in contentViews.withIndex()) {
                    if(contentView.visibility == View.VISIBLE) {
                        contentStrings.add(contentEditTexts[index].text.toString())
                    }
                }

                Log.d("remove","기존 내용 $contentStrings")

                // remove 한 index의 내용을 삭제
                contentStrings.removeAt(index)
                Log.d("remove","삭제 인덱스 $index 내용 $contentStrings")

                // 가장 뒤에서 visible인 contentView를 Gone 처리
               for((index,contentView) in contentViews.reversed().withIndex()) {
                    if(contentView.visibility == View.VISIBLE) {
                        contentView.visibility = View.GONE
                        contentEditTexts[2-index].setText("")
                        Log.d("remove","삭제된 et $index 내용 ${contentEditTexts[index].text.toString()}")
                        break
                    }
                }

                // Visible인 Content들에 SetText
                var count = 0
                for((index,contentView) in contentViews.withIndex()) {
                    if(contentView.visibility == View.VISIBLE) {
                        contentEditTexts[index].setText(contentStrings[index])
                        count ++
                    }
                }
                Log.d("remove","남은 카운트 $count")

                //Strings 배열 초기화
                contentStrings.clear()
                if(count != 3) {
                    binding.ivAddContent.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes

        // 앱을 실행한 디바이스의 가로, 세로 크기를 가져온다.
        val deviceWidth = getDeviceSize(requireActivity()).x

        // 다이얼로그 크기를 디바이스 가로의 90%로 설정한다.
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

}