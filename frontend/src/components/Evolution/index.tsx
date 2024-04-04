import './index.scss';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import { onionNameRecord, onionRecord } from '@/utils/onionRecord';

interface EvolutionProps {
  before: number;
  after: number;
  isTutorial?: boolean;
}

const Evolution = ({ before, after, isTutorial }: EvolutionProps) => {
  const [isEvolve, setIsEvolve] = useState<boolean>(false);

  const evolve = () => {
    if (isEvolve) return;

    const circles = [...document.getElementsByClassName('circle')];
    const bubbles = [...document.getElementsByClassName('bubble')];
    const others = document.getElementsByClassName('current');

    for (let i = 0; i < others.length; i++) {
      const curr = others[i] as HTMLElement;
      curr.style.visibility = 'hidden';
    }

    const animationTime = 12;

    setTimeout(
      () => {
        for (let i = 0; i < circles.length; i++) {
          const circle = circles[i] as HTMLElement;
          circle.style.animation = '';
        }
        for (let i = 0; i < bubbles.length; i++) {
          const bubble = bubbles[i] as HTMLElement;
          bubble.style.animation = '';
        }
        setIsEvolve(true);
      },
      500 + animationTime * 1000,
    );

    for (let i = 0; i < circles.length; i++) {
      const circle = circles[i] as HTMLElement;
      circle.style.animation = `tunnel ${animationTime}s linear ${i * 0.1}s`;
    }
    for (let i = 0; i < bubbles.length; i++) {
      const bubble = bubbles[i] as HTMLElement;
      bubble.style.animation = `bubble .4s reverse ${animationTime - 2 + i * 0.05}s`;
    }

    const beforeEvolve = document.getElementsByClassName(
      'before',
    )[0] as HTMLElement;
    beforeEvolve.style.animation = `evolve-out ${animationTime}s forwards`;

    const afterEvolve = document.getElementsByClassName(
      'after',
    )[0] as HTMLElement;
    afterEvolve.style.animation = `evolve-in ${animationTime}s forwards`;

    const background = document.getElementsByClassName(
      'background',
    )[0] as HTMLElement;
    background.style.animation = `background-out ${animationTime}s`;
  };

  useEffect(() => {
    evolve();
  }, []);

  return (
    <section className='background'>
      <div className='circle c1' />
      <div className='circle c2' />
      <div className='circle c3' />
      <div className='circle c4' />
      <div className='circle c5' />
      <div className='evolve'>
        <div className='bubble-wrap'>
          {Array.from({ length: 20 }, (_, index) => index + 1).map((value) => (
            <div className='bubble' key={value} />
          ))}
        </div>
        <img
          className='onion before current'
          src={`/images/onions/onion-${onionRecord[before]}.png`}
          alt='before evolve'
        />
        <img
          className='onion after'
          src={`/images/onions/onion-${onionRecord[after]}.png`}
          alt='after evolve'
        />
      </div>
      {isEvolve ? (
        <Link to={isTutorial ? '/tutorial' : '/choose'}>
          <div className='message'>
            축하합니다! {onionNameRecord[before]}(은)는 <br />
            {onionNameRecord[after]}(으)로 진화했습니다!🔻
          </div>
        </Link>
      ) : (
        <div className='message'>
          ...... 오잉!? <br />
          {onionNameRecord[before]}의 상태가......!
        </div>
      )}
    </section>
  );
};

export default Evolution;
