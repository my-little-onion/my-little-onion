import styled from '@emotion/styled';

import theme from '@/styles/theme';

const MainBackground = styled.main`
  background-image: url('/images/backgrounds/main-background.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 100svh;
`;

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

const MainPage = () => {
  return (
    <MainBackground>
      <TitleWrapper>
        <TitleOnion src='/images/onions/onion-common.png' />
        <Title>
          나<LittleTitle>만의</LittleTitle>
          <br />작<LittleTitle>은</LittleTitle>
          <br />양<LittleTitle>파</LittleTitle>
        </Title>
      </TitleWrapper>
    </MainBackground>
  );
};

export default MainPage;
