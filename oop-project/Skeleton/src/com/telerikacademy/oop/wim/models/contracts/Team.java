package com.telerikacademy.oop.wim.models.contracts;

import com.telerikacademy.oop.wim.models.BoardImpl;
import com.telerikacademy.oop.wim.models.MemberImpl;

import java.util.Map;

public interface Team {
    
    String getName();

    Map<String, BoardImpl> getAllBoards();

     Map<String, MemberImpl> getAllMembers();

      void addMemberToTeam(String memberName, MemberImpl member);

    
//   List<BoardMember> getFurnitures();
//
//   void add(BoardMember boardMember);
//
//   void remove(BoardMember boardMember);
//
//   BoardMember find(String model);
//
//   String catalog();
    
}
