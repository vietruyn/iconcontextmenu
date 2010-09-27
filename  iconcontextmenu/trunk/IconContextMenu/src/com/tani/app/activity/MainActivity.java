/*
 * Copyright (C) 2010 Tani Group 
 * http://android-demo.blogspot.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tani.app.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import com.tani.app.ui.IconContextMenu;

/**
 * Main activity for the sample
 * @author nguyendt
 *
 */
public class MainActivity extends Activity {
	
	private final int CONTEXT_MENU_ID = 1;
	private IconContextMenu iconContextMenu = null;
	
	private final int MENU_ITEM_1_ACTION = 1;
	private final int MENU_ITEM_2_ACTION = 2;
	private final int MENU_ITEM_3_ACTION = 3;
	private final int MENU_ITEM_4_ACTION = 4;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        
        //init sample list for test
        initSampleList();
        
        //init the menu
        iconContextMenu = new IconContextMenu(this, CONTEXT_MENU_ID);
        iconContextMenu.addItem(res, "Menu Item 1", R.drawable.ic_1, MENU_ITEM_1_ACTION);
        iconContextMenu.addItem(res, "Menu Item 2", R.drawable.ic_2, MENU_ITEM_2_ACTION);
        iconContextMenu.addItem(res, "Menu Item 3", R.drawable.ic_3, MENU_ITEM_3_ACTION);
        iconContextMenu.addItem(res, "Menu Item 4", R.drawable.ic_4, MENU_ITEM_4_ACTION);
        
        //set onclick listener for context menu
        iconContextMenu.setOnClickListener(new IconContextMenu.IconContextMenuOnClickListener() {
			@Override
			public void onClick(int menuId) {
				switch(menuId) {
				case MENU_ITEM_1_ACTION:
					Toast.makeText(getApplicationContext(), "You've clicked on menu item 1", 1000).show();
					break;
				case MENU_ITEM_2_ACTION:
					Toast.makeText(getApplicationContext(), "You've clicked on menu item 2", 1000).show();
					break;
				case MENU_ITEM_3_ACTION:
					Toast.makeText(getApplicationContext(), "You've clicked on menu item 3", 1000).show();
					break;
				case MENU_ITEM_4_ACTION:
					Toast.makeText(getApplicationContext(), "You've clicked on menu item 4", 1000).show();
					break;
				}
			}
		});
    }
    
    /**
     * initialize sample list for demo application
     */
    private void initSampleList() {
    	ListView lv = (ListView) this.findViewById(R.id.sampleList);
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
    												android.R.layout.simple_list_item_1,
    												new String[] {"List Item 1", "List Item 2",
    																"List Item 3", "List Item 4",
    																"List Item 5", "List Item 6",
    																"List Item 7", "List Item 8"});
    	lv.setAdapter(adapter);
    	lv.setOnItemLongClickListener(itemLongClickHandler);
    }
    
    /**
     * list item long click handler
     * used to show the context menu
     */
    private OnItemLongClickListener itemLongClickHandler = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			showDialog(CONTEXT_MENU_ID);
			return true;
		}
	};

	/**
	 * create context menu
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == CONTEXT_MENU_ID) {
			return iconContextMenu.createMenu("Menu Title");
		}
		return super.onCreateDialog(id);
	}
}