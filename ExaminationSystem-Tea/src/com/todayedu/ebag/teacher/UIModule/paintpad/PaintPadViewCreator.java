package com.todayedu.ebag.teacher.UIModule.paintpad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.Database.SDCard;
import com.todayedu.ebag.teacher.UIModule.BaseActivity;
import com.todayedu.ebag.teacher.UIModule.paintpad.interfaces.PaintViewCallBack;
import com.todayedu.ebag.teacher.UIModule.paintpad.utils.BitMapUtils;
import com.todayedu.ebag.teacher.UIModule.paintpad.utils.ImageButtonTools;
import com.todayedu.ebag.teacher.UIModule.paintpad.utils.PaintConstants;
import com.todayedu.ebag.teacher.UIModule.paintpad.view.ColorView;
import com.todayedu.ebag.teacher.UIModule.paintpad.view.PaintView;

/**
 * 生成画板View
 * 
 * @author Craig Lee
 * 
 */
public class PaintPadViewCreator implements View.OnClickListener {

	private View view;
	private BaseActivity context;
	private ViewGroup viewGroup;

	// PaintView
	private PaintView mPaintView = null;

	// button 界面上的各个按钮
	private ImageButton saveButton = null;
	private ImageButton eraserButton = null;
	private ImageButton penSizeButton = null;
	private ImageButton undoButton = null;
	private ImageButton redoButton = null;
	private ImageButton toButtonLayoutButton = null;
	private ImageButton toColorLayoutButton = null;

	// 一共8个ColorView
	private ColorView colorView1 = null;
	private ColorView colorView2 = null;
	private ColorView colorView3 = null;
	private ColorView colorView4 = null;
	private ColorView colorView5 = null;
	private ColorView colorView6 = null;
	private ColorView colorView7 = null;
	private ColorView colorView8 = null;

	// 通过控制Layout来控制某些变化
	private LinearLayout colorLayout = null;
	private LinearLayout buttonLayout = null;
	private LinearLayout paintViewLayout = null;
	private LinearLayout eraserSizeLayout = null;
	private LinearLayout penSizeLayout = null;
	private LinearLayout shapLayout = null;
	private LinearLayout shapLayoutf = null;

	// 一些RadioGroup和重要的(也就是默认的)RadioButton
	private RadioGroup colorRadioGroup = null;
	private RadioGroup eraserSizeRadioGroup = null;
	private RadioButton eraserSizeRadio01 = null;
	private RadioGroup penSizeRadioGroup = null;
	private RadioButton penSizeRadio1 = null;
	private RadioGroup shapRadioGroup = null;
	private RadioGroup shapRadioGroupf = null;
	private RadioButton curvRadioButton = null;

	// 用于两个SizeRadioGroup的一些操作
	private boolean clearCheckf = false;
	private boolean clearCheck = false;

	private List<ColorView> mColorViewList = null;
	// 使用PenType临时存储选择的变量，当创建时再传给PaintView
	private int mPenType = PaintConstants.PEN_TYPE.PLAIN_PEN;

	/**
	 * 
	 * @param context
	 * @param viewGroup
	 * @param examId
	 * @param problemId
	 * @param mode
	 */
	public PaintPadViewCreator(BaseActivity context, ViewGroup viewGroup) {

		this.context = context;
		this.viewGroup = viewGroup;
		init();
	}

	private void init() {

		this.view = View.inflate(context, R.layout.paintmain, viewGroup);
		initLayout();
		initButtons();
		initColorViews();
		initPaintView();
		initCallBack();
		initShapRadioGroups();
	}

	/**
	 * 初始化所用到的Layout
	 */
	private void initLayout() {

		colorLayout = (LinearLayout) findViewById(R.id.paint_LinearLayoutColor);
		buttonLayout = (LinearLayout) findViewById(R.id.paint_buttonLayout);
		colorRadioGroup = (RadioGroup) findViewById(R.id.paint_radioGroupColor);
		paintViewLayout = (LinearLayout) findViewById(R.id.paint_paintViewLayout);
		eraserSizeLayout = (LinearLayout) findViewById(R.id.paint_sizeSelectLayout);
		penSizeLayout = (LinearLayout) findViewById(R.id.paint_sizeSelectLayoutPen);
		eraserSizeLayout.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.popbackground4));
		penSizeLayout.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.popbackground4));
		shapLayout = (LinearLayout) findViewById(R.id.paint_shapSelectLayout1);
		shapLayoutf = (LinearLayout) findViewById(R.id.paint_shapSelectLayout2);
	}

	/**
	 * 初始化所有的Button
	 */
	private void initButtons() {

		findButtonById();
		setBackGroundDrawable();
		List<ImageButton> list = initButtonList();
		for (ImageButton imageButton : list) {
			ImageButtonTools.setButtonFocusChanged(imageButton);
			imageButton.setOnClickListener(this);
		}
	}

	/**
	 * 找到所有的通过所有的button
	 */
	private void findButtonById() {

		saveButton = (ImageButton) findViewById(R.id.paint_imageButtonSave);
		eraserButton = (ImageButton) findViewById(R.id.paint_imageButtonEraser);
		penSizeButton = (ImageButton) findViewById(R.id.paint_imageButtonPen);

		undoButton = (ImageButton) findViewById(R.id.paint_imageButtonUndo);
		redoButton = (ImageButton) findViewById(R.id.paint_imageButtonRedo);

		toButtonLayoutButton = (ImageButton) findViewById(R.id.paint_imageButtonToButtonLayout);
		toColorLayoutButton = (ImageButton) findViewById(R.id.paint_imageButtonToColorLayout);
	}

	/**
	 * 初始化所有Button的Drawable
	 */
	private void setBackGroundDrawable() {

		eraserButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.eraser));
		saveButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.save));
		penSizeButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.pen_default));
		redoButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.cantredo));
		undoButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.cantundo));
		toButtonLayoutButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.changetobuttonlayout));
		toColorLayoutButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.changetocolorlayout));
	}

	/**
	 * 将需要处理的ImageButton加入到List中
	 */
	private List<ImageButton> initButtonList() {

		List<ImageButton> list = new ArrayList<ImageButton>();
		// list.add(clearButton);
		list.add(eraserButton);
		list.add(saveButton);
		list.add(penSizeButton);
		list.add(undoButton);
		list.add(redoButton);
		list.add(toButtonLayoutButton);
		list.add(toColorLayoutButton);
		return list;
	}

	/**
	 * 初始化颜色选择的View
	 */
	private void initColorViews() {

		// 读取preference
		SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);

		// 如果配置文件不存在，则使用默认值
		colorView1 = new ColorView(this.context, settings.getInt("color1",
		        PaintConstants.COLOR1));
		colorView2 = new ColorView(this.context, settings.getInt("color2",
		        PaintConstants.COLOR2));
		colorView3 = new ColorView(this.context, settings.getInt("color3",
		        PaintConstants.COLOR3));
		colorView4 = new ColorView(this.context, settings.getInt("color4",
		        PaintConstants.COLOR4));
		colorView5 = new ColorView(this.context, settings.getInt("color5",
		        PaintConstants.COLOR5));
		colorView6 = new ColorView(this.context, settings.getInt("color6",
		        PaintConstants.COLOR6));
		colorView7 = new ColorView(this.context, settings.getInt("color7",
		        PaintConstants.COLOR7));
		colorView8 = new ColorView(this.context, settings.getInt("color8",
		        PaintConstants.COLOR8));
		initColorRadioGroup();
	}

	/**
	 * 初始化颜色选择的RadioGroup
	 */
	private void initColorRadioGroup() {

		mColorViewList = new ArrayList<ColorView>();
		mColorViewList.add(colorView1);
		mColorViewList.add(colorView2);
		mColorViewList.add(colorView3);
		mColorViewList.add(colorView4);
		mColorViewList.add(colorView5);
		mColorViewList.add(colorView6);
		mColorViewList.add(colorView7);
		mColorViewList.add(colorView8);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		        PaintConstants.COLOR_VIEW_SIZE, PaintConstants.COLOR_VIEW_SIZE);
		params.setMargins(10, 5, 10, 5);

		for (ColorView colorView : mColorViewList) {
			colorRadioGroup.addView(colorView, params);
			colorView.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
				        boolean isChecked) {

					for (ColorView colorView : mColorViewList) {
						if (buttonView.equals(colorView)
						        && buttonView.isChecked()) {
							setToLastPenTeype();
							mPaintView.setPenColor(colorView.getColor());
						}
					}
				}
			});
		}
	}

	/**
	 * 如果是Eraser则Set到上一个PenType
	 */
	private void setToLastPenTeype() {

		if (mPaintView.getCurrentPainter() == PaintConstants.PEN_TYPE.ERASER) {
			mPaintView.setCurrentPainterType(mPenType);
		}
	}

	/**
	 * 初始化画画所用的paintView
	 */
	private void initPaintView() {

		mPaintView = new PaintView(this.context);
		paintViewLayout.addView(mPaintView);
	}

	/**
	 * 初始化paintView的回调函数
	 */
	private void initCallBack() {

		mPaintView.setCallBack(new PaintViewCallBack() {

			// 当画了之后对Button进行更新
			@Override
			public void onHasDraw() {

				enableUndoButton();
				disableRedoButton();
			}

			// 当点击之后让各个弹出的窗口都消失
			@Override
			public void onTouchDown() {

				setAllLayoutInvisable();
			}
		});
	}

	private void enableUndoButton() {

		undoButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.undo));
	}

	private void disableRedoButton() {

		redoButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.cantredo));
	}

	/**
	 * 将所有的布局全都隐藏
	 */
	private void setAllLayoutInvisable() {

		shapLayout.setVisibility(View.INVISIBLE);
		shapLayoutf.setVisibility(View.INVISIBLE);
		penSizeLayout.setVisibility(View.INVISIBLE);
		eraserSizeLayout.setVisibility(View.INVISIBLE);
	}

	/**
	 * 初始化所有的RadioGroup
	 */
	private void initShapRadioGroups() {

		shapRadioGroup = (RadioGroup) findViewById(R.id.paint_shapRadioGroup);
		curvRadioButton = (RadioButton) findViewById(R.id.paint_RadioButtonShapCurv);
		shapRadioGroupf = (RadioGroup) findViewById(R.id.paint_shapRadioGroupf);
		initEraseSizeGroup();
		initPenSizeGroup();
		initShapRadioGroup();
		initShapRadioGroupf();
	}

	/**
	 * 初始化EraserSize选择布局
	 */
	private void initEraseSizeGroup() {

		eraserSizeRadioGroup = (RadioGroup) findViewById(R.id.paint_eraseRaidoGroup);
		eraserSizeRadio01 = (RadioButton) findViewById(R.id.paint_RadioButtonEraser01);
		eraserSizeRadio01.setChecked(true);
		eraserSizeRadioGroup
		        .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			        
			        @Override
			        public void onCheckedChanged(RadioGroup group, int checkedId) {
			        
				        switch (checkedId) {
							case R.id.paint_RadioButtonEraser01:
								mPaintView
								        .setEraserSize(PaintConstants.ERASER_SIZE.SIZE_1);
								break;
							case R.id.paint_RadioButtonEraser02:
								mPaintView
								        .setEraserSize(PaintConstants.ERASER_SIZE.SIZE_2);
								break;
							case R.id.paint_RadioButtonEraser03:
								mPaintView
								        .setEraserSize(PaintConstants.ERASER_SIZE.SIZE_3);
								break;
							case R.id.paint_RadioButtonEraser04:
								mPaintView
								        .setEraserSize(PaintConstants.ERASER_SIZE.SIZE_4);
								break;
							case R.id.paint_RadioButtonEraser05:
								mPaintView
								        .setEraserSize(PaintConstants.ERASER_SIZE.SIZE_5);
								break;
							default:
								break;
						}
					}
		        });
	}

	/**
	 * 初始化负责确定Pensize的radioGroup
	 */
	private void initPenSizeGroup() {

		penSizeRadioGroup = (RadioGroup) findViewById(R.id.paint_penRaidoGroup);
		penSizeRadio1 = (RadioButton) findViewById(R.id.paint_RadioButtonPen01);
		penSizeRadio1.setChecked(true);
		penSizeRadioGroup
		        .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			        
			        @Override
			        public void onCheckedChanged(RadioGroup group, int checkedId) {
			        
				        switch (checkedId) {
							case R.id.paint_RadioButtonPen01:
								mPaintView
								        .setPenSize(PaintConstants.PEN_SIZE.SIZE_1);
								break;
							case R.id.paint_RadioButtonPen02:
								mPaintView
								        .setPenSize(PaintConstants.PEN_SIZE.SIZE_2);
								break;
							case R.id.paint_RadioButtonPen03:
								mPaintView
								        .setPenSize(PaintConstants.PEN_SIZE.SIZE_3);
								break;
							case R.id.paint_RadioButtonPen04:
								mPaintView
								        .setPenSize(PaintConstants.PEN_SIZE.SIZE_4);
								break;
							case R.id.paint_RadioButtonPen05:
								mPaintView
								        .setPenSize(PaintConstants.PEN_SIZE.SIZE_5);
								break;
							default:
								break;
						}
					}
		        });
	}

	/**
	 * 初始化第一个ShapRadioGroup
	 */
	private void initShapRadioGroup() {

		curvRadioButton.setChecked(true);
		shapRadioGroup
		        .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			        
			        @Override
			        public void onCheckedChanged(RadioGroup group, int checkedId) {
			        
				        // 如果当前没有选中
				        if (checkedId == -1) {
					        return;
				        }
				        /**
				         * 不需要每次都调用
				         */
				        if (clearCheckf == false) {
					        clearCheck = true;
					        shapRadioGroupf.clearCheck();
				        }
				        mPaintView.setPenStyle(Paint.Style.STROKE);
				        switch (checkedId) {
							case R.id.paint_RadioButtonShapCurv:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.CURV);
								break;
							case R.id.paint_RadioButtonShapRect:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.RECT);
								break;
							case R.id.paint_RadioButtonShapCircle:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.CIRCLE);
								break;
							case R.id.paint_RadioButtonShapOval:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.OVAL);
								break;
							case R.id.paint_RadioButtonShapSquare:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.SQUARE);
								break;
							default:
								break;
						}
						clearCheck = false;
					}
		        });
	}

	private void initShapRadioGroupf() {

		shapRadioGroupf
		        .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			        
			        @Override
			        public void onCheckedChanged(RadioGroup group, int checkedId) {
			        
				        // 如果当前没有选中
				        if (checkedId == -1) {
					        return;
				        }
				        if (clearCheck == false) {
					        clearCheckf = true;
					        shapRadioGroup.clearCheck();
				        }
				        mPaintView.setPenStyle(Paint.Style.FILL);
				        switch (checkedId) {
							case R.id.paint_RadioButtonShapLine:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.LINE);
								mPaintView.setPenStyle(Paint.Style.STROKE);
								break;
							case R.id.paint_RadioButtonShapRectf:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.RECT);
								break;
							case R.id.paint_RadioButtonShapCirclef:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.CIRCLE);
								break;
							case R.id.paint_RadioButtonShapOvalf:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.OVAL);
								break;
							case R.id.paint_RadioButtonShapSquaref:
								mPaintView
								        .setCurrentShapType(PaintConstants.SHAP.SQUARE);
								break;
							default:
								break;
						}
						clearCheckf = false;
					}
		        });
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.paint_imageButtonSave:
				onClickButtonSave();
				break;

			case R.id.paint_imageButtonEraser:
				onClickButtonEraser();
				break;

			case R.id.paint_imageButtonPen:
				onCLickButtonPen();
				break;

			case R.id.paint_imageButtonUndo:
				onClickButtonUndo();
				break;

			case R.id.paint_imageButtonRedo:
				onClickButtonRedo();
				break;

			case R.id.paint_imageButtonToColorLayout:
				onClickButtonToColorLayout();
				break;

			case R.id.paint_imageButtonToButtonLayout:
				onClickButtonToButtonLayout();
				break;

			default:
				break;
		}
	}

	/**
	 * 保存
	 */
	public void onClickButtonSave() {
	
		if (!mPaintView.canUndo())
			return;
		setAllLayoutInvisable();
		SDCard sdCard = new SDCard();
		File file = null;
		
		try {
			
			String fileFullPath = "";// TODO:get file full path
			if (sdCard.isFileExistedWithFullPath(fileFullPath)) {
				file = new File(fileFullPath);
			} else {
				file = sdCard.createSDFile("", "");
			}

		} catch (Throwable e) {
			e.printStackTrace();
			this.context.showToast("SD卡出错里啦，无法保存！><");
		}
		Bitmap bitmap = mPaintView.getSnapShoot();
		boolean result = BitMapUtils.saveToSdCard(file, bitmap);
		if (result) {
			saveToDB(file);
		} else {
			this.context.showToast("暂存至数据库失败！><");
		}

	}

	/**
	 * 把文件位置保存到数据库
	 * 
	 * @param file
	 */
	private void saveToDB(File file) {
	
		boolean result = false;// TODO:save file to db;
		if (result)
			this.context.showToast("保存成功！^^");
		else
			this.context.showToast("无法暂存到数据库！><");
	}

	/**
	 * 载入已经存在的图片
	 */
	public void loadExsitedImage() {
	
		SDCard sdCard = new SDCard();
		String filePath = "";// TODO:get file path
		Log.i("PaintPadViewCreator", "loadExsitedImage:" + filePath);
		if (sdCard.isFileExistedWithFullPath(filePath)) {
			Bitmap bitmap = BitMapUtils.loadFromSdCard(filePath);
			mPaintView.setForeBitMap(bitmap);
			mPaintView.resetState();
			upDateUndoRedo();
		}
	}

	/**
	 * 清空
	 */
	private void onClickButtonClear() {

		setAllLayoutInvisable();
		new AlertDialog.Builder(context)
		        .setMessage("确定清空画板么？")
		        .setPositiveButton(R.string.comm_confirm,
		                new OnClickListener() {
			                
			                @Override
			                public void onClick(DialogInterface dialog,
			                        int which) {
			                
				                mPaintView.clearAll();
				                mPaintView.resetState();
				                upDateUndoRedo();
				                upDateColorView();
				                resetSizeView();
			                }
		                })
		        .setNegativeButton(R.string.comm_cancel, new OnClickListener() {
			        
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			        
				        return;
			        }
		        }).show();
	}

	/**
	 * 进入下一题之前复位
	 * 
	 * @param examId
	 * @param problemId
	 */
	public void reset(int examId, int problemId) {

		mPaintView.clearAll();
		mPaintView.resetState();
		upDateUndoRedo();
		upDateColorView();
		resetSizeView();
		loadExsitedImage();
	}

	/**
	 * 将ColorView的Check清空
	 */
	private void upDateColorView() {

		colorRadioGroup.clearCheck();
	}

	/**
	 * 重置sizeView
	 */
	private void resetSizeView() {

		penSizeRadio1.setChecked(true);
		eraserSizeRadio01.setChecked(true);
		curvRadioButton.setChecked(true);
	}

	/**
	 * 更新UndoRedo Button
	 */
	private void upDateUndoRedo() {

		if (mPaintView.canUndo()) {
			enableUndoButton();
		} else {
			disableUndoButton();
		}
		if (mPaintView.canRedo()) {
			enableRedoButton();
		} else {
			disableRedoButton();
		}
	}

	/**
	 * 橡皮
	 */
	private void onClickButtonEraser() {

		if (eraserSizeLayout.isShown()) {
			setAllLayoutInvisable();
		} else {
			eraserSizeLayout.setVisibility(View.VISIBLE);
			penSizeLayout.setVisibility(View.INVISIBLE);
			shapLayout.setVisibility(View.INVISIBLE);
			shapLayoutf.setVisibility(View.INVISIBLE);
		}
		mPaintView.setCurrentPainterType(PaintConstants.PEN_TYPE.ERASER);
	}

	/**
	 * 改变画笔的大小
	 */
	private void onCLickButtonPen() {

		mPaintView.setCurrentPainterType(mPenType);
		if (penSizeLayout.isShown()) {
			setAllLayoutInvisable();
		} else {
			penSizeLayout.setVisibility(View.VISIBLE);
			shapLayout.setVisibility(View.VISIBLE);
			shapLayoutf.setVisibility(View.VISIBLE);
			eraserSizeLayout.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * undo
	 */
	private void onClickButtonUndo() {

		setAllLayoutInvisable();
		mPaintView.undo();
		upDateUndoRedo();
	}

	/**
	 * redo
	 */
	private void onClickButtonRedo() {

		setAllLayoutInvisable();
		mPaintView.redo();
		upDateUndoRedo();
	}

	/**
	 * 去颜色选择界面
	 */
	private void onClickButtonToColorLayout() {

		setAllLayoutInvisable();
		setToLastPenTeype();
		buttonLayout.setVisibility(View.INVISIBLE);
		colorLayout.setVisibility(View.VISIBLE);
	}

	/**
	 * 去ButtonLayout（主界面）
	 */
	private void onClickButtonToButtonLayout() {

		buttonLayout.setVisibility(View.VISIBLE);
		colorLayout.setVisibility(View.GONE);
	}

	private void enableRedoButton() {

		redoButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.redo));
	}

	private void disableUndoButton() {

		undoButton.setBackgroundDrawable(getResources().getDrawable(
		        R.drawable.cantundo));
	}

	private View findViewById(int id) {

		return this.view.findViewById(id);
	}

	private Resources getResources() {

		return this.context.getResources();
	}

	private SharedPreferences getPreferences(int mode) {

		return this.context.getPreferences(mode);
	}

	public View getView() {

		return view;
	}
	
	public void setbg(Bitmap bitmap) {
	
		// mPaintView.clearAll();
		mPaintView.setForeBitMap(bitmap);
		// mPaintView.resetState();
		upDateUndoRedo();
	}

}