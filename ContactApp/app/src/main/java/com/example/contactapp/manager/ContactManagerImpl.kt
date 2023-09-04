package com.example.contactapp.manager

import com.example.contactapp.model.Contact

/**
 *
 * 싱글톤 형태로 구현
 * val contactManagerImpl : ContactManagerImpl = ContactManagerImpl.getInstance() 형태로 가져와서 사용
 *
 */
class ContactManagerImpl private constructor() : ContactManager {
    private val contactList = mutableListOf<Contact>()
    private var id = 0

    /**
     *
     * 새로운 Contact를 생성하고 리스트에 넣어주는 함수
     *
     */
    init {
        contactList.add(Contact(id++, "test3", "01023415253", "test1@gmail.com", 0, "ic_launcher_foreground", false))
        contactList.add(Contact(id++, "test2", "01011112223", "test2@gmail.com", 0, "ic_launcher_foreground", false))
        contactList.add(Contact(id++, "test1", "01034562233", "test3@gmail.com", 0, "ic_launcher_foreground", false))
        contactList.add(Contact(id++, "test4", "01026412253", "test4@gmail.com", 0, "ic_launcher_foreground", false))
        contactList.add(Contact(id++, "test5", "01021410253", "test5@gmail.com", 0, "ic_launcher_foreground", false))
    }
    override fun createContact(name: String, phone: String, email: String, alarm: Int) {
        val contactA = Contact(id++, name, phone, email, alarm, "user", false)
        contactList.add(contactA)
    }

    /**
     *
     * id를 받아와 Contact 를 업데이트 하는 함수
     *
     */
    override fun updateContact(id: Int, contact: Contact) {
        var idx = -1

        for (i in contactList.indices) {
            if (contactList[i].id == id) {
                idx = i
            }
        }

        if (idx == -1) {
            return
        }

        contactList[idx] = contact
    }

    /**
     *
     * 새로운 Contact 고유번호를 받아 그에 해당하는 Contact를 리턴해주는 함수
     *
     */
    override fun getContactById(id: Int): Contact? {
        for (contact in contactList)
            if (contact.id == id) {
                return contact
            }

        return null
    }

    /**
     *
     * 현재 Contact 리스트를 반환 해주는 함수
     *
     */
    override fun getContactList(): List<Contact> {
        return contactList
    }

    companion object {
        private var instance: ContactManagerImpl? = null

        fun getInstance(): ContactManagerImpl {
            return instance ?: synchronized(this) {
                instance ?: ContactManagerImpl().also {
                    instance = it
                }
            }
        }
    }


}