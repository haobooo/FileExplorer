package net.micode.fileexplorer;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class FileListNoCursorAdapter extends BaseAdapter {
	private ArrayList<File> mFileList;
	
	private Context mContext;
	private FileIconHelper mFileIcon;
	private FileViewInteractionHub mFileViewInteractionHub;
	
	public FileListNoCursorAdapter(Context context,
            FileViewInteractionHub f, FileIconHelper fileIcon) {
		mContext = context;
		mFileViewInteractionHub = f;
		mFileIcon = fileIcon;
	}
	
	@Override
	public int getCount() {
		if (mFileList != null) {
			return mFileList.size();
		}
		
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if ( mFileList == null) {
			return null;
		}
		
		return mFileList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.category_file_browser_item, parent, false);
		}
		
		File file = (File) getItem(position);
		FileInfo fileInfo = new FileInfo();
        fileInfo.filePath = file.getPath();
        fileInfo.fileName = file.getName();
        fileInfo.fileSize = file.length();
        fileInfo.ModifiedDate = file.lastModified();
        
        FileListItem.setupFileListItemInfo(mContext, convertView, fileInfo, mFileIcon,
                mFileViewInteractionHub);
        convertView.findViewById(R.id.category_file_checkbox_area).setOnClickListener(
                new FileListItem.FileItemOnClickListener(mContext, mFileViewInteractionHub));
		
		return convertView;
	}
	
	public void setList(ArrayList<File> list) {
		mFileList = list;
		this.notifyDataSetChanged();
	}
}