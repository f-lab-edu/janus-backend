INSERT INTO check_questions (question_type, sort_order, text, primary_axis, primary_score, secondary_axis, secondary_score) VALUES
('SELF', 1, '답장을 보고도 머릿속으로만 답장한 적이 많다', 'R', 2, 'O', 1),
('SELF', 2, '밤이 되면 갑자기 배고프거나 감성이 올라온다', 'N', 2, 'M', 1),
('SELF', 3, '할 일은 많은데 일단 누워 있고 싶다', 'O', 2, 'P', 1),
('SELF', 4, 'SNS에 올릴 때 현실보다 조금 더 멀쩡해 보이고 싶다', 'S', 2, 'X', 1),
('SELF', 5, '계획을 세우는 순간 이미 반쯤 이룬 기분이다', 'P', 2, 'G', 1),
('SELF', 6, '마감이 가까워져야 집중력이 켜진다', 'D', 2, 'P', 1),
('SELF', 7, '갓생 살겠다고 선언한 적이 자주 있다', 'G', 2, 'P', 1),
('SELF', 8, '할인/추천/후기를 보면 갑자기 사고 싶어진다', 'B', 2, 'M', 1),
('SELF', 9, '숏폼, 알림, 새 콘텐츠를 계속 확인하게 된다', 'M', 2, 'O', 1),
('SELF', 10, '나를 위한 선물이라는 말에 쉽게 설득된다', 'X', 2, 'B', 1),
('SELF', 11, '해야 할 일을 미루면서 다른 쓸데없는 일을 열심히 한다', 'P', 2, 'D', 1),
('SELF', 12, '쉬고 있어도 뭔가 해야 할 것 같아 마음이 불편하다', 'O', 2, 'G', 1);

INSERT INTO check_questions (question_type, sort_order, text, primary_axis, primary_score, secondary_axis, secondary_score) VALUES
('FRIEND', 1, '이 사람은 답장이 늦어도 악의는 없어 보인다', 'R', 2, 'O', 1),
('FRIEND', 2, '밤에 특히 활동량이나 텐션이 올라가는 편이다', 'N', 2, 'M', 1),
('FRIEND', 3, '자주 피곤해 보이거나 방전되어 있다', 'O', 2, NULL, NULL),
('FRIEND', 4, 'SNS 속 모습과 실제 모습의 온도 차가 있다', 'S', 2, 'X', 1),
('FRIEND', 5, '계획은 잘 세우는데 실행은 자주 밀리는 편이다', 'P', 2, 'G', 1),
('FRIEND', 6, '마감이 가까워질수록 갑자기 사람이 달라진다', 'D', 2, 'P', 1),
('FRIEND', 7, '갓생 살겠다고 선언하는 순간이 자주 있다', 'G', 2, 'P', 1),
('FRIEND', 8, '충동적으로 사고 나중에 후회하는 편이다', 'B', 2, 'N', 1),
('FRIEND', 9, '짧은 자극이나 재미있는 것에 쉽게 끌린다', 'M', 2, 'B', 1),
('FRIEND', 10, '돈 쓸 때 표정이 제일 살아나는 편이다', 'X', 2, 'B', 1);
