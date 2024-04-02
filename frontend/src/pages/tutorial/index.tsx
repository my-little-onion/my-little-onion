import styled from '@emotion/styled';
import { Link, useNavigate } from 'react-router-dom';

import { onionNameRecord } from '@/utils/onionRecord';
import { OnionTitle } from '@/pages/choose';
import theme from '@/styles/theme';

import Background from '@/components/Background';
import Button from '@/components/Button';
import Onion from '@/components/Onion';

import { IconArrowLeft, IconRecordStart } from '#/svgs';

const TutorialWrapper = styled.section`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const ButtonWrapper = styled.div`
  position: absolute;
  top: 10px;
  left: 10px;
`;

const SvgWrapper = styled.div`
  position: absolute;
  top: 50%;
  transform: translate(40%, -50%);
`;

const TutorialMessage = styled.div`
  margin-top: 16px;
`;

const TutorialPage = () => {
  const navigate = useNavigate();

  const handleRecordClick = () => {
    navigate('/evolution', {
      state: { before: 1, after: 11, isTutorial: true },
    });
  };

  return (
    <Background>
      <TutorialWrapper>
        <Link to='/'>
          <ButtonWrapper>
            <Button
              type='button'
              svg={<IconArrowLeft width={60} height={60} />}
            />
          </ButtonWrapper>
        </Link>
        <OnionTitle>{onionNameRecord[1]}</OnionTitle>
        <Onion categoryId={1} />
        <Button
          type='button'
          color={theme.color.blue}
          size='medium'
          onClick={handleRecordClick}
        >
          <SvgWrapper>
            <IconRecordStart width={25} height={25} />
          </SvgWrapper>
          <span>양파에게 말걸어보기</span>
        </Button>
        <TutorialMessage>
          튜토리얼에선 음성 인식을 지원하지 않아요!
        </TutorialMessage>
      </TutorialWrapper>
    </Background>
  );
};

export default TutorialPage;
