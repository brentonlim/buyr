package edu.towson.cosc435.buyr.lists

class ListItemHolder {
    //dummy data to test//
    //dummy data to test//
    //dummy data to test//
    //dummy data to test//

    companion object{
        fun createDataSet(): ArrayList<ListItem>{
            val list = ArrayList<ListItem>()
            list.add(
                ListItem(
                    "001",
                    "This is a list item.",
                    "01/23/2020"
                )
            )
            list.add(
                ListItem(
                    "002",
                    "This is a list item.",
                    "01/23/2020"
                )
            )
            list.add(
                ListItem(
                    "003",
                    "This is a list item.",
                    "01/23/2020"
                )
            )
            list.add(
                ListItem(
                    "004",
                    "This is a list item.",
                    "01/23/2020"
                )
            )
            list.add(
                ListItem(
                    "005",
                    "This is a list item.",
                    "01/23/2020"
                )
            )

            return list
        }
    }
}