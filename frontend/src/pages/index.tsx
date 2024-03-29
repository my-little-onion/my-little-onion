import styled from '@emotion/styled';
import { Link } from 'react-router-dom';

import theme from '@/styles/theme';

import Background from '@/components/Background';

const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 80svh;
`;

const Title = styled.h1`
  position: relative;
  font-size: 10vh;
  text-shadow:
    -8px 0 white,
    0 8px white,
    8px 0 white,
    0 -8px white;
  z-index: ${theme.zIndex.title};
`;

const LittleTitle = styled.span`
  font-size: 7vh;
  font-weight: 800;
  z-index: ${theme.zIndex.title};
`;

const TitleOnion = styled.img`
  width: auto;
  height: 8vh;
  position: absolute;
  top: 21svh;
  z-index: ${theme.zIndex.titleCharacter};
`;

const LoginButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const MainPage = () => {
  return (
    <Background>
      <TitleWrapper>
        <TitleOnion src='/images/onions/onion-common.png' alt='onionCommon' />
        <Title>
          나<LittleTitle>만의</LittleTitle>
          <br />작<LittleTitle>은</LittleTitle>
          <br />양<LittleTitle>파</LittleTitle>
        </Title>
      </TitleWrapper>
      <LoginButtonWrapper>
        <Link to='/choose'>
          <img
            src='/images/icons/button-kakao-login.png'
            alt='buttonKakaoLogin'
          />
        </Link>
      </LoginButtonWrapper>
    </Background>
  );
};

export default MainPage;
